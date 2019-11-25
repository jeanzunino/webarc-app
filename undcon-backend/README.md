# undcon-backend

REST API for UNDCON Project from [UNDCON](https://github.com/jeanzunino/webarc-app).

## Pre-requisites

1. JDK 8 or higher
2. Maven 4.0.0 or newer

## Build

```
mvn clean package
```

## Run

- Create schema with name cliente1
- Execute the scripts of diretory [/script/migration/public](https://github.com/jeanzunino/webarc-app/tree/master/undcon-backend/script/migration/public)
- Execute the scripts of diretory [/script/migration/tenants](https://github.com/jeanzunino/webarc-app/tree/master/undcon-backend/script/migration/tenants)
- Execute com.undcon.app.Application
```

## Postman Test

- Run Backend
- Do it login in localhost:8080/login, Body: {"login" "admin@cliente1", "password": "12345678"}
- Save the token.
- In new Request, Put in the HEADER a parameter "Authorization" with value of token.

