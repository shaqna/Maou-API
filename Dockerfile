FROM openjdk:18.0.1.1-oracle

COPY /build/libs/maou-api-0.0.1-SNAPSHOT.jar /app/maou-api.jar

CMD ["java", "-jar", "/app/maou-api.jar"]
