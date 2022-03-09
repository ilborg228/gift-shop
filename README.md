# gift-shop
###### GiftShop REST API

### Prerequisites
* [Java Development Kit (JDK) 11](https://www.oracle.com/java/technologies/downloads/#java11)
* [Maven 3](https://maven.apache.org/download.cgi)

### Technology stack:
* Spring Boot
* Spring Data JPA
* MySQL

All endpoints for CRUD operations can be seen in:
 - [EndpointsController](https://github.com/ilborg228/gift-shop/blob/master/src/main/java/ru/samara/giftshop/controller/EndpoitsController.java) 
```

For any listed request:
/category/** - endpoint for CRUD operations on categories  
/product/** - endpoint for CRUD operations on products
```
Build and Run
-------------

1. Run in the command line:
    ```
    mvn clean install spring-boot:run
    ```

2. Open `http://localhost:8080` in a web browser.
