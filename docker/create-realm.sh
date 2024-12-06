#!/bin/bash

# Стартуем Keycloak
/opt/jboss/keycloak/bin/kc.sh start-dev &

# Ждем несколько секунд, чтобы Keycloak успел запуститься
sleep 20

# Создаем реалм
curl -X POST http://localhost:8080/realms \
  -H "Content-Type: application/json" \
  -d '{
    "realm": "'$KEYCLOAK_REALM'",
    "enabled": true,
    "sslRequired": "external",
    "publicKey": "",
    "accessTokenLifespan": 3600
  }'

# Создаем клиента
curl -X POST http://localhost:8080/realms/$KEYCLOAK_REALM/clients \
  -H "Content-Type: application/json" \
  -d '{
    "clientId": "'$KEYCLOAK_CLIENT_ID'",
    "enabled": true,
    "publicClient": '$KEYCLOAK_PUBLIC_CLIENT',
    "redirectUris": ["http://localhost:8081/*"],
    "webOrigins": ["http://localhost:8081"]
  }'

# Создаем пользователя
curl -X POST http://localhost:8080/realms/$KEYCLOAK_REALM/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john.doe",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "enabled": true
  }'

# Устанавливаем пароль пользователю
USER_ID=$(curl -X GET http://localhost:8080/realms/$KEYCLOAK_REALM/users?username=john.doe | jq -r '.[0].id')
curl -X PUT http://localhost:8080/realms/$KEYCLOAK_REALM/users/$USER_ID/reset-password \
  -H "Content-Type: application/json" \
  -d '{
    "type": "password",
    "value": "password",
    "temporary": false
  }'

# Ожидаем, чтобы Keycloak завершил настройку
wait
