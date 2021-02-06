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