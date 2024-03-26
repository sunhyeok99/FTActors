FROM mysql:8.3

ADD  ./docker/init_db /docker-entrypoint-initdb.d

RUN chmod 775 /docker-entrypoint-initdb.d/init.sql

ENV MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}

EXPOSE 3306