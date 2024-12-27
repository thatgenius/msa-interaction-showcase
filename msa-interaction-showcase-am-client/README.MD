Приложение представляет собой библиотеку для получения токена из Account Manager'а. Токен используется для доступа к Wiki-Fuzzy-Searcher'у.

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
@EnableConfigurationProperties({YourSpringAppProperties.class, AmClientProperties.class})
```
3. В файле с настройками (.yml) вашего МС'а указать credentials и URL к Account Manager'у. Если этого не сделать будет использован адрес по умолчанию (см. AmClientProperties.java)
```
msa:
  integration:
    showcase:
      am-client:
        account-manager:
          url: 'http://localhost:8081'
        am-login: "admin"
        am-password: "admin"
```
4. Объявить в вашем МС бин (например в конфигурационном классе, помеченном @Configuration):
```
@Bean
public TokenClient createTokenClient() {
    return new TokenClient();
}
``` 

5. Получить токен в ваших компонентах Spring (контроллерах, сервисах и т. д.) можно так:
```
@Autowired
private TokenClient tokenClient;
...
tokenClient.retrieve(
    amClientProperties.getAccountManager().getUrl(),
    amClientProperties.getAmLogin(),
    amClientProperties.getAmPassword()
)
```