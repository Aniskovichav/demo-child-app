#FROM quay.io/keycloak/keycloak:21.1.1
#
#ENV KEYCLOAK_ADMIN=admin
#ENV KEYCLOAK_ADMIN_PASSWORD=admin
#
#WORKDIR /opt/keycloak
#
#ENTRYPOINT ["./bin/kc.sh", "start-dev"]

# Используем официальный образ Keycloak
FROM quay.io/keycloak/keycloak:21.0.1

# Задаем переменные окружения для создания начального настроенного реалма
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin
ENV KEYCLOAK_REALM=myrealm
ENV KEYCLOAK_CLIENT_ID=family-app
ENV KEYCLOAK_PUBLIC_CLIENT=true

WORKDIR /opt/keycloak

# Создаем реалм, клиента и пользователя
COPY create-realm.sh /opt/jboss/tools/

# Запуск скрипта для настройки
RUN chmod +x /opt/jboss/tools/create-realm.sh

# Стартуем Keycloak
ENTRYPOINT ["/opt/jboss/tools/create-realm.sh"]
CMD ["/opt/jboss/tools/start-keycloak.sh"]
