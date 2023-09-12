FROM eclipse-temurin:17
LABEL authors="sarath"

WORKDIR /app

COPY target/EmailService-0.0.1-SNAPSHOT.jar /app/EmailService.jar

ENTRYPOINT ["java", "-jar", "EmailService.jar"]