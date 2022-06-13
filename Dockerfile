FROM azul/zulu-openjdk-alpine:11
COPY build/libs/helpdesk-0.0.1-SNAPSHOT.jar helpdesk.jar
EXPOSE 8080
CMD ["java", "-jar", "helpdesk.jar"]