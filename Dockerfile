FROM eclipse-temurin:21-jdk-alpine

ARG REDIRECT_URL

ENV REDIRECT_URL=${REDIRECT_URL}

COPY ./build/libs/*SNAPSHOT.jar project.jar

ENTRYPOINT ["java", "-jar", "project.jar"]