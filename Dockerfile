FROM eclipse-temurin:21

WORKDIR /app

copy target/inventory_app-1.0.0-SNAPSHOT.jar /app/inventory-app.jar

LABEL app-name="inventory-app"
EXPOSE 8080

CMD ["java", "-jar", "inventory-app.jar"]