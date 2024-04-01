FROM rabbitmq


RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    erlang

#RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange
ENV HOSTNAME=${RABBITMQ_HOST}

EXPOSE 5672 15672 61613

ENV RABBITMQ_DEFAULT_USER=${RABBITMQ_USERNAME}
ENV RABBITMQ_DEFAULT_PASS=${RABBITMQ_PASSWORD}

RUN rabbitmq-plugins enable rabbitmq_stomp
RUN rabbitmq-plugins enable rabbitmq_web_stomp
RUN rabbitmq-plugins enable rabbitmq_web_stomp_examples
RUN rabbitmq-plugins enable rabbitmq_management


CMD ["rabbitmq-server"]