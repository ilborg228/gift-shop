# gift-shop
###### GiftShop REST API with JWT authentication

### Prerequisites
* [Java Development Kit (JDK) 11](https://www.oracle.com/java/technologies/downloads/#java11)
* [Maven 3](https://maven.apache.org/download.cgi)

### Technology stack:
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Spring Security
* JWT (JSON Web Tokens)

There are three user accounts :
```
Admin - admin:admin
User - user:password
Disabled - disabled:password (this user is disabled)
```

All endpoints for CRUD operations can be seen in:
 - [EndpointsController](https://github.com/ilborg228/gift-shop/blob/master/src/main/java/ru/samara/giftshop/controller/EndpoitsController.java) 
```
/auth - authentication endpoint (HTTP method: POST) - place your credentials in JSON format in request body as JwtAuthenticationRequest 

Use Bearer Token for any listed request:
/category/** - endpoint for CRUD operations on categories (a valid JWT token must be present in the request header)   
/product/** - endpoint for CRUD operations on products (a valid JWT token must be present in the request header)     
```
Build and Run
-------------

1. Run in the command line:
    ```
    mvn clean install spring-boot:run
    ```

2. Open `http://localhost:8080` in a web browser.

To create tables, execute main.sql
