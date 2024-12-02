1. Сборка Docker образа и запуск
Собираем проект и создаем JAR:

mvn clean package
Собираем Docker образ:

docker build -t your-dockerhub-username/child-service:latest .
Запускаем образ локально (для проверки):

docker run -p 8080:8080 your-dockerhub-username/child-service:latest

2. Запуск Kubernetes с Minikube
   Запускаем Minikube:

minikube start
Создаем namespace:

kubectl create namespace demo
Применяем манифесты:

kubectl apply -f kubernetes/ -n demo
Проверяем статус подов:

kubectl get pods -n demo
Открываем сервис:

minikube service child-service -n demo



Проверка REST API через Postman позволяет убедиться, что ваш сервер работает корректно, а все CRUD-методы выполняются согласно требованиям. Вот пошаговая инструкция:

1. Настройка среды в Postman
   Установите Postman: Скачайте и установите Postman отсюда.
   Создайте новую коллекцию: Это удобный способ группировать запросы по проектам.
   Настройте переменные среды:
   Перейдите в Environment Settings (настройки среды).
   Добавьте базовый URL API, например:
   key: base_url
   value: http://localhost:8080/api/v1
   Теперь во всех запросах можно использовать {{base_url}}, что упростит изменения в будущем.
2. CRUD-операции для Child
   Предположим, ваш API работает по пути /api/v1/children.

2.1. Create (POST)
URL: {{base_url}}/children
Метод: POST
Body: Выберите формат raw -> JSON и введите:
{
"name": "John",
"age": 5
}
Отправьте запрос и проверьте, что статус ответа — 201 Created.
2.2. Read All (GET)
URL: {{base_url}}/children
Метод: GET
Отправьте запрос. Вы должны увидеть список всех детей:
[
{
"id": 1,
"name": "John",
"age": 5
},
{
"id": 2,
"name": "Alice",
"age": 7
}
]
2.3. Read by ID (GET)
URL: {{base_url}}/children/1
Метод: GET
Отправьте запрос. Вы получите данные по конкретному ребенку:
{
"id": 1,
"name": "John",
"age": 5
}
2.4. Update (PUT)
URL: {{base_url}}/children/1
Метод: PUT
Body: Выберите raw -> JSON и введите обновленные данные:
{
"name": "John Updated",
"age": 6
}
Отправьте запрос. Убедитесь, что статус ответа — 200 OK.
2.5. Delete (DELETE)
URL: {{base_url}}/children/1
Метод: DELETE
Отправьте запрос. Проверьте статус ответа — 204 No Content.

3. Работа с авторизацией (Keycloak)
   Если ваш API защищен Keycloak, вам потребуется:

3.1. Получить токен
URL: http://<keycloak-host>/realms/<realm-name>/protocol/openid-connect/token
Метод: POST
Headers:
Content-Type: application/x-www-form-urlencoded
Body: Введите данные клиента:
grant_type=password
client_id=<client-id>
client_secret=<client-secret>
username=<username>
password=<password>
Отправьте запрос. Вы получите ответ с токеном:
{
"access_token": "eyJhbGciOi...",
"expires_in": 3600
}
3.2. Добавить токен в запросы
Перейдите в коллекцию -> Authorization -> выберите Bearer Token.
Вставьте access_token.
Теперь все запросы из этой коллекции будут использовать токен.
