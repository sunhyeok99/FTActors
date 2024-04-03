FROM rabbitmq

#RUN apt-get update && \
#        apt-get install -y --no-install-recommends \
#        wget && \
#        wget https://packages.erlang-solutions.com/erlang-solutions_2.0_all.deb && \
#        dpkg -i erlang-solutions_2.0_all.deb && \
#        apt-get update && \
#        apt-get install -y --no-install-recommends \
#        erlang && \
#        rm -rf /var/lib/apt/lists/*

#RUN apt-get update && apt-get install -y apt-transport-https
#RUN apt-get update && apt-get install -y rabbitmq-server

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
    rabbitmq-plugins enable rabbitmq_management && \
    rabbitmq-plugins enable rabbitmq_federation

CMD ["rabbitmq-server"]
