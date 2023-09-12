FROM ubuntu:latest
LABEL authors="sarath"

WORKDIR /app

COPY target/StockService-0.0.1-SNAPSHOT.jar /app/StockService.jar

ENTRYPOINT ["java", "-jar", "StockService.jar"]