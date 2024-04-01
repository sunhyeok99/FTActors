FROM rabbitmq

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    erlang

RUN apt-get update && apt-get install -y rabbitmq-server

CMD ["rabbitmq-server"]

#RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange
ENV HOSTNAME=${RABBITMQ_HOST}

EXPOSE 5672 15672 61613

ENV RABBITMQ_DEFAULT_USER=${RABBITMQ_USERNAME}
ENV RABBITMQ_DEFAULT_PASS=${RABBITMQ_PASSWORD}
# 필요한 RabbitMQ 플러그인 활성화

RUN rabbitmq-plugins enable rabbitmq_stomp && \
    rabbitmq-plugins enable rabbitmq_web_stomp && \
    rabbitmq-plugins enable rabbitmq_web_stomp_examples && \
    rabbitmq-plugins enable rabbitmq_mqtt && \
    rabbitmq-plugins enable rabbitmq_web_mqtt && \
    rabbitmq-plugins enable rabbitmq_web_mqtt_examples && \
    rabbitmq-plugins enable rabbitmq_management

CMD ["rabbitmq-server"]
