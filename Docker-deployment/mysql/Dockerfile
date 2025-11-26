FROM mysql:8

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=shopping-cart

COPY init.sql /docker-entrypoint-initdb.d/init.sql
