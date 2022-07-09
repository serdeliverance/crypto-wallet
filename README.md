# crypto-wallet

A demo crypto wallet app using with teaching purposes. This app allows clients to manage their crypto currency portfolio. It uses [Coinmarketcap API](https://coinmarketcap.com/api/) for getting the updated cryptocurrencies quotation.

It provides the following functionalities:

* managing users (CRUD operations, uses for admins)
* buy, sell and transfer crypto currencies
* allow users to see their portfolio balance (the quotation of their cryptocurrencies portfolio converted to USD)
* allows users to see their transaction history

## Tech Stack

* Java 11
* Spring Boot
* Postgres
* Docker

## Architecture

The following diagram shows the systems architecture:

![Alt text](diagrams/cw-architecture.png?raw=true "Architecture") 

## Data Model

The following diagram shows the data model:

![Alt text](diagrams/cw-data-model.png?raw=true "Title") 

## Run the app

1. Start containers using docker compose:

```
docker-compose up
```

2. Run the application

You'll need to have configured the following env variables before running the app:

* `DB_URL`
* `DB_USER`
* `DB_PASSWORD`
* `COINMARKETCAP_URL`: [Coinmaerkap](https://coinmarketcap.com/api/) api url
* `COINMARKETCAP_API_KEY`: you have to generate one API KEY on [Coinmaketcap Web Site](https://coinmarketcap.com/api/)

Then execute:

```
mvn spring-boot:run
```

It runs the application on http://localhost:8080


## Database migrations

We use [Flyway](https://flywaydb.org/) for database migrations. It runs the migration on application startup, so remember to have the dockers up when running the app.

## API

* Healthcheck

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/healthcheck
```

* Get user by id

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/users/:userId
```

* Get all users

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/users
```

* Create user

```
curl -X POST -H "Content-Type: application/json" \
    -d '{"username": "pepe", "password": "pass1234", "email": "pepe@gmail.com"}' \
    http://localhost:8080/users
```

* Update user

```
curl -X PUT -H "Content-Type: application/json" \
    -d '{"username": "pepe", "password": "pass1234", "email": "pepe@gmail.com"}' \
    http://localhost:8080/users/:userId
```

* Delete user

```
curl -X "DELETE" http://localhost:8080/users/:userId
```

* Get quotes

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/cryptocurrencies/quotes
```

* Get portfolio

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/portfolios/:userId
```

* Get transaction history

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/transactions/8
```

* Transfer

```
curl -X POST -H "Content-Type: application/json" \
    -d '{"issuer": "88", "receiver": "2", "cryptocurrency": "Huobi Token", "amount": 10}' \
    http://localhost:8080/transactions/transferences
```

* Buy

```
curl -X POST -H "Content-Type: application/json" \
    -d '{"userId": 3, "cryptocurrency": "Bitcoin", "amountInUsd": 100000}' \
    http://localhost:8080/transactions/buys
```

* Sell

```
curl -X POST -H "Content-Type: application/json" \
    -d '{"userId": 3, "cryptocurrency": "Bitcoin", "amount": 1}' \
    http://localhost:8080/transactions/sells
```

## Extra notes

* A ready [docker-compose](docker-compose.yml) is provided. It contains all the components ready for local development. It also contains dummy data for playing around with the app.

* Also, a [postman collection](postman-collection/crypto.postman_collection.json) is provided.