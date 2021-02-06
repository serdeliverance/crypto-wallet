# crypto-wallet

A demo crypto wallet app using with teaching purposes. This app allows clients to manage their crypto currency portfolio. It uses [Coinmarketcap API](https://coinmarketcap.com/api/) for getting accurate cryptocurrencies quotation.

It provides the following functionalities:

* managing users (CRUD operations, uses for admins)
* buy, sell and transfer crypto currencies
* allow users to see their portfolio balance (the quotation of their cryptocurrencies portfolio converted to USD)
* allows users to see their transaction history

## Tech Stack

* Java 11
* Spring Boot
* Postgres
* Kafka
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

```
mvn spring-boot:run
```

It runs the application on http://localhost:8080