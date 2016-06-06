# spring-boot-web-kafka-producer
Simple Inventory CRUD Application using spring-boot and kafka

- this project required:
    - apache maven
    - IDE
    - mongodb installed in PC

- run mongodb:
    - open run (`window + r`)
    - type `mongod --dbpath C:\data`

- run zookeper: `zkserver` in command prompt or terminal

- run kafka: `.\bin\windows\kafka-server-start.bat .\config\server.properties`

- show producer and consumer in directory windows in kafka\bin directory:
  - producer:
      `kafka-console-producer.bat --broker-list localhost:9092 --topic [topic-name]`
  - consumer:
      `kafka-console-consumer.bat --zookeeper localhost:2181 --topic [topic-name]`

- running application
    - make sure zookeeper, kafka, mongodb is running
    - `go to project directory in command prompt / terminal`
    - `mvn spring-boot:run`
    - `open localhost:8080` in browser

    - Application behaviour:
      There is updatePrice Button to update the price of some inventory. the
      behaviour is when you update the price with new_price is bigger than old
      (price discount) price, then the application will automatically publish
      event to Kafka broker.