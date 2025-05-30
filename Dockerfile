FROM openjdk:17

WORKDIR /app

COPY target/estoque-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]