ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/elasticsearch/elasticsearch:${ELASTIC_VERSION}

COPY ./config /usr/share/elasticsearch/config

RUN ["pwd"]
VOLUME ["$(pwd):/usr/share/elasticsearch/data"]

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install analysis-nori

# Add your elasticsearch plugins setup here
# Example: RUN elasticsearch-plugin install analysis-icu
