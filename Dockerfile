FROM openjdk:17-jdk-slim

WORKDIR /app

copy target/inventory_app-0.0.1-SNAPSHOT.jar /app/inventory-app.jar

EXPOSE 8080

CMD ["java", "-jar", "inventory-app.jar"]