# crypto-wallet

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

## Extra notes

## Extra notes

* A ready [docker-compose](docker-compose.yml) is provided. It contains all the components ready for local development. It also contains dummy data for playing around with the app.

* Also, a [postman collection](postman-collection/crypto.postman_collection.json) is provided.