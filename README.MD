== Содержание данного файла README.MD ==
- Введение;
- Краткое описание;
- Инструкция по запуску и использованию приложения;
- Подробное описание каждого maven-модуля.

== Введение ==
Данное приложение - программный продукт, разработанный в соответствии с микросервисной архитектурой (MSA), состоит из 6 отдельных maven-модулей: 4-ёх микросервисов и 2-ух библиотек: access-клиент и модуль с общими ресурсами (DTO и т. п.)). Я использовал следующие технические средства: Java 11, Docker (контейниризация), Spring (Boot, Data), PostgreSQL, REST API (reactive web client, формат обмена данными: JSON, XML), токен авторизация (custom solution), Unit-тесты, логирование значимых событий, происходящих в программе.

Автор данного приложения: Жбанов Евгений (email для контактов: thatgenius4@gmail.com).

== Краткое описание ==

Приложение фактически решает следующие 2 задачи:
1. служит демонстрацией моих навыков как разработчика ПО на основе решения максимально простой и понятной идеологической задачи (см. далее пункт 2);
2. получает случайное слово (далее keyword) из https://random-word-api.vercel.app и обращается с ним в Wikipedia API (https://en.wikipedia.org/w/api.php), где мы получаем страницу, содержание которой, максимально близко соответствует значению ключевого слова (keyword). Результат отдает через REST API в виде XML;

Приложение демонстрирует различные технические решения промышленных задач (это основная цель данной программы) и не пытается решить 2-ую задачу прямо (см. выше).  

== Инструкция по запуску и использованию приложения ==

Для работы приложения необходимо чтобы были свободны порты локального хоста с 8080 по 8085.

1. Необходимо подготовить среду для выполнения микросервиса (далее МС) Account-Manager. Для этого необходима работающая БД POstgreSQL с подготовленой 1-ой таблицей и одной записью в ней. Для этих целей я использовал Docker-контейнер. Шаги, которые необходимо выполнить, см. в README.MD maven-модуля "msa-interaction-showcase-account-manager";
2. Последовательно запустите следующие 4 Java-программы:
- msa-interaction-showcase-orchestrator/src/main/java/zhbanov/evgenii/orchestrator/OrchestratorApplication.java
- msa-interaction-showcase-account-manager/src/main/java/zhbanov/evgenii/accountmanager/AccountManagerApplication.java
- msa-interaction-showcase-keyword-supplier/src/main/java/zhbanov/evgenii/keywordsupplier/KeywordSupplierApplication.java
- msa-interaction-showcase-wiki-fuzzy-searcher/src/main/java/zhbanov/evgenii/wikifuzzysearcher/WikiFuzzySearcherApplication.java
3. Перейдите по адресу http://localhost:8080/random-wiki-page/, например в браузере или SoapUI.
Пример ответа из SoapUI:
HTTP/1.1 200
Content-Type=application/xml;charset=UTF-8
Transfer-Encoding=chunked
Date=Fri, 27 Dec 2024 06:34:06 GMT
Keep-Alive=timeout=60
Connection=keep-alive

<SearchSuggestionDto><randomKeyword>key</randomKeyword><suggestedWikiPageUri>https://en.wikipedia.org/wiki/Key</suggestedWikiPageUri></SearchSuggestionDto>

== Подробное описание каждого maven-модуля == 

1. Модуль Keyword-Supplier.
Это servlet-приложение - поставщик промежуточных JSON-данных (= случайное слово) в данном программном продукте. Получает данные из общедоступного API (https://random-word-api.vercel.app). Keyword-Supplier требует авторизации по токену. Токены выдаёт другой микросервис - Account-Manager. Keyword-Supplier также обращается в Account-Manager для проверки токена и получения сведений о пользователе, предоставившем его;
2. Модуль Wiki-Fuzzy-Searcher.
Это servlet-приложение - поставщик данных: принимает слово - keyword (см. выше пункт 1), отдаёт XML c URI-адресом страницы Wikipedia, содержание которой, максимально близко соответствует значению ключевого слова (keyword). Получает данные из Wikipedia API (https://en.wikipedia.org/w/api.php) в XML, парсит их, достаёт необходимую информацию и отдаёт извлеченную информацию также в XML;
3. Модуль Account-Manager. 
Это servlet-приложение - менеджер учетных записей. Сведения хранятся в PostgreSQL. Данное приложение предоставляет следующие функции: 1) выдает токены доступа к Keyword-Supplier'у (необходимы логин и пароль от учетной записи); 2) по токену предоставляет краткую информацию о конкретной учетной записи (УЗ) с логином и паролем которой, был выдан вышеупомянутый токен;
4. Модуль AM-Client.
Это библиотека - access-клиент для получения токенов из Account-Manager'а (далее AM). Принимает логин и пароль от УЗ в AM и возвращает токен-строку;
5. Модуль Orchestrator.
Это servlet-приложение - поставщик данных (выдает случайные страницы на Wikipedia). Используя библиотеку AM-Client, получает токен из Account-Manager'а, затем благодаря нему получает keyword из Keyword-Supplier'а и далее делает запрос с этим ключевым словом в Wiki-Fuzzy-Searcher, который выдаёт необходимый URI (подробее см. выше в пункте 2). Полученный URI отдаёт в JSON в качестве ответа по REST API;
6. Модуль Commons.
Это библиотека со статическими ресурсами (модельные классы и т. п.), используемыми во всех остальных модулях.