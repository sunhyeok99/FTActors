FROM openjdk:17

WORKDIR /app

COPY ./FTActors-be/build/libs/actors-0.0.1-SNAPSHOT.jar /app

COPY docker/env/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh


ENTRYPOINT ["/wait-for-it.sh","mysql:3306", "--", "java", "-jar", "actors-0.0.1-SNAPSHOT.jar", "--spring.config.additional-location=classpath:/config.yml"]