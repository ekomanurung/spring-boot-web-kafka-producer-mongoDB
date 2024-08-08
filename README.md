[![Build Status](https://travis-ci.org/ekomanroe/spring-boot-web-kafka-producer-mongoDB.svg?branch=master)](https://travis-ci.org/ekomanroe/spring-boot-web-kafka-producer-mongoDB)

# spring-boot-web-kafka-producer
Simple Inventory CRUD Application using spring-boot and kafka

## Prerequisites
- Docker installed on local machine
- Java 17
  - for running springboot 3
- Maven

## How to run
- run make `run-app` to start the application
- run make `stop-app` to stop the application

## Using the application
- open in browser http://localhost:8080
- use `Add` to add new inventory
- to see the data sent to kafka, click updatePrice button
- update price with new price less than old price
- logs will appear in the console

## Open Swagger
- open in browser http://localhost:8080/swagger-ui/index.html
- now you can use apps using swagger
