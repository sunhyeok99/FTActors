
FROM docker.elastic.co/elasticsearch/elasticsearch:8.12.2

ENV discovery.type=single-node
ENV ES_JAVA_OPTS="-Xms512m -Xmx512m"

# COPY elasticsearch.yml /usr/share/elasticsearch/config/
VOLUME /usr/share/elasticsearch/data

EXPOSE 9200