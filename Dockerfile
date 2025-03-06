FROM openjdk:17-jdk-slim

#ARG APP_VERSION

WORKDIR /app

#COPY target/ms-migraciones-poc-${APP_VERSION}.jar app.jar
COPY target/ms-migraciones-poc-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]