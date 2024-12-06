FROM mysql:8.2

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=family
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root

COPY ./init-scripts /docker-entrypoint-initdb.d/
EXPOSE 3306
