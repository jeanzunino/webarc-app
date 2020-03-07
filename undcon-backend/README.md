# undcon-backend

REST API for UNDCON Project from [UNDCON](https://github.com/jeanzunino/webarc-app).

## Pre-requisites

1. JDK 12 or higher
2. Eclipse 2019-09 (4.13.0) or higher.

## Build

```
mvn clean package
```

## Run

- Create database with name db
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

## API's

/login

API's de cadastro
Todas as API's de cadastro abaixo possuirão um método GET com paginação. Exemplo: GET /users?page=0&size=20

/users 
/providers
/productCategories
/products
/permissions
/incomes - FALTA PADRONIZAR PARA UTILIZAR O SERVICE (FALTA PAGINAR, FALTA PERMISSÂO)
/expenses - FALTA PADRONIZAR PARA UTILIZAR O SERVICE (FALTA PAGINAR, FALTA PERMISSÂO)
/employees - FALTA PADRONIZAR PARA UTILIZAR O SERVICE (FALTA PAGINAR, FALTA PERMISSÂO)
/customers - FALTA PADRONIZAR PARA UTILIZAR O SERVICE (FALTA PAGINAR, FALTA PERMISSÂO)
/serviceTypes

API's de Configurações

/tenants
/configuration FALTA PERMISSÂO
/menus - FALTA PAGINAR, FALTA PERMISSÂO