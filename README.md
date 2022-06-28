# KITM Application

Admin UI is written with Spring MVC.

Client UI is implemented in Angular and uses JWT for stateless backend authentication.

## Configuration

MySQL/MariaDb configuration:

Connection string could be found in the `backend/src/main/resources/config` folder as `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/application
...
```

Database and user configuration:

```sql
DROP USER IF EXISTS 'application'@'%';
DROP DATABASE IF EXISTS application;
CREATE DATABASE application;
GRANT ALL PRIVILEGES ON application.* TO 'application'@'%' IDENTIFIED BY '${KITM_DATASOURCE_PASSWORD}';
FLUSH PRIVILEGES;
```

Database schema:

![db](https://github.com/votuscode/kitm-application/blob/develop/backend/src/main/resources/database/schema.png?raw=true)

## Application configuration

Application secrets are located in the `backend/src/main/resources/config` folder as `application-secrets.properties`

```properties
spring.datasource.username=application
spring.datasource.password=

spring.security.user.password=

jwt.secret=

admin.password=
user.password=
```

## Compile and run

Compile and run from the project root folder:

```bash
mvn clean install
java -jar backend/target/backend-1.0-SNAPSHOT.jar
```

## Docker

Build and run docker image:

```bash
docker build -t kitm-application .
docker run -p 8080:8080 --env-file .env -it kitm-application bash
```
