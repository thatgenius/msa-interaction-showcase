Подготовим таблицу в БД PostgreSQL, воспользовавшись Docker-контейнером:

docker run --name msa-interaction-showcase-postgres -p 8085:5432 -e POSTGRES_PASSWORD=123 -d postgres
docker container ls // to find out CONTAINER ID
docker exec -it <CONTAINER ID> sh

Docker console:
su - postgres
psql -v ON_ERROR_STOP=1

CREATE USER showcase_user;
CREATE DATABASE showcase_database;
GRANT ALL PRIVILEGES ON DATABASE showcase_database TO showcase_user;
\c showcase_database
set role showcase_user;
create schema account_manager_schema;
// select current_database();
// select current_user;
set schema 'account_manager_schema';
// select current_schema();
create table account_manager_schema.account (id bigserial primary key, login varchar(150) UNIQUE NOT NULL, password varchar(25) NOT NULL, name varchar(150) NOT NULL, token varchar(32) UNIQUE);
insert into account_manager_schema.account (login, password, name) values ('admin', 'admin', 'Ivan');