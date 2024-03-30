ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/logstash/logstash:${ELASTIC_VERSION}

RUN curl -L -o mysql-connector-java-8.0.28.tar.gz https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.28.tar.gz && \
    tar -xzf mysql-connector-java-8.0.28.tar.gz && \
    mv mysql-connector-java-8.0.28/mysql-connector-java-8.0.28.jar /usr/share/logstash/logstash-core/lib/jars/

COPY ./config /usr/share/logstash/config
COPY ./pipeline/logstash.conf /usr/share/logstash/pipeline/logstash.conf

# Add your logstash plugins setup here
# Example: RUN logstash-plugin install logstash-filter-json
