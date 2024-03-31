FROM rabbitmq

#RUN apt-get update && \
##apt-get install -y curl unzip
###
###RUN curl https://dl.bintray.com/rabbitmq/community-plugins/3.7.x/rabbitmq_delayed_message_exchange/rabbitmq_delayed_message_exchange-20171201-3.7.x.zip > rabbitmq_delayed_message_exchange-20171201-3.7.x.zip && \
###unzip rabbitmq_delayed_message_exchange-20171201-3.7.x.zip && \
###rm -f rabbitmq_delayed_message_exchange-20171201-3.7.x.zip && \
###mv rabbitmq_delayed_message_exchange-20171201-3.7.x.ez plugins/

#RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange
ENV HOSTNAME=${RABBITMQ_HOST}

EXPOSE 5672 15672 61613

ENV RABBITMQ_DEFAULT_USER=${RABBITMQ_USERNAME}
ENV RABBITMQ_DEFAULT_PASS=${RABBITMQ_PASSWORD}

RUN rabbitmq-plugins enable rabbitmq_stomp

CMD ["rabbitmq-server"]