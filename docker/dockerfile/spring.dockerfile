
FROM openjdk:17
ENV TZ Asia/Seoul
ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY ./FTActors-be/build/libs/actors-0.0.1-SNAPSHOT.jar .

COPY docker/env/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# 컨테이너를 구동할 때 실행할 명령어 지정(명렁어를 스페이스로 나눈것과 같다)
ENTRYPOINT ["/wait-for-it.sh","mysql:3306", "--", "java", "-jar", "actors-0.0.1-SNAPSHOT.jar", "--spring.config.additional-location=classpath:/config.yml"]