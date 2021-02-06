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