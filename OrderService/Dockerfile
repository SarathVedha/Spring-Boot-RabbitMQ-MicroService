FROM eclipse-temurin:17
LABEL authors="sarath"

WORKDIR /app

COPY target/OrderService-0.0.1-SNAPSHOT.jar /app/OrderService.jar

ENTRYPOINT ["java", "-jar", "OrderService.jar"]