# gift-shop
###### GiftShop REST API

### Prerequisites
* [Java Development Kit (JDK) 11](https://www.oracle.com/java/technologies/downloads/#java11)
* [Maven 3](https://maven.apache.org/download.cgi)
* MySQL 8
* [RabbitMQ](https://www.rabbitmq.com/download.html)

### Technology stack:
* Spring Boot
* Spring Data JPA
* MySQL
* RabbitMQ
* Liquibase


### This project implements microservice architecture:

* catalog-service: everything related to the product catalog
* notification-service: need to send emails by SMTP
* discovery-service: Eureka server
* auth: responsible for authorization and authentication (not working right now)

Build and Run
-------------

1. Move to service base package

2. Run in the command line:
    ```
    mvn clean install spring-boot:run
    ```

3. Open `http://localhost:8080` in a web browser.
