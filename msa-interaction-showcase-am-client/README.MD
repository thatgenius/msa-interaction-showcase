Приложение представляет собой библиотеку для получения токена из Account Manager'а. Токен используется для доступа к Intermediate Data Supplier'у.

Использование:
1. В pom.xml добавить зависимость:

```
<dependency>
   <groupId>zhbanov.evgenii</groupId>
   <artifactId>msa-interaction-showcase-am-client</artifactId>
   <version>1.0</version>
</dependency>
```

2. В @EnableConfigurationProperties вашего МС'а указать класс с настройками клиента - AmClientProperties, например, это может выглядеть так: 
```
@EnableConfigurationProperties({TestProperties.class, AmClientProperties.class})
```
3. В файле с настройками (.yml) вашего МС'а указать URL к Account Manager'у. Если этого не сделать будет использован адрес по умолчанию (см. AmClientProperties.java)
```
msa:
  integration:
    showcase:
      account-manager-url: "http://localhost:8081"
```
4. Объявить в вашем МС 2 бина (например в конфигурационном классе, помеченном @Configuration):
```
@Bean
public TokenClient createTokenClient() {
    return new TokenClient();
}
```
где amClientProperties - это бин с настройками клиента (AmClientProperties.java). 

5. Получить токен в ваших компонентах Spring (контроллерах, сервисах и т. д.) можно так:
```
@Autowired
private TokenClient tokenClient;
...
tokenClient.getToken(
    amClientProperties.getAccountManagerUrl(),
    amClientProperties.getLogin(),
    amClientProperties.getPassword()
)
```