FROM gradle:7.4.2-jdk11 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle -g $BUILD_CACHE_PATH clean build -x test

FROM azul/zulu-openjdk-alpine:11
COPY --from=builder /home/gradle/src/build/libs/helpdesk-0.0.1-SNAPSHOT.jar helpdesk.jar
EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=dev", "-jar", "helpdesk.jar"]