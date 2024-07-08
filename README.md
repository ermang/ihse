# ihse

ihse is the case study implementation for Ing Hubs Sample Exercise  

## Features:
* Create Stock
* Update Stock Price
* Delete Stock
* Create Exchange  
* Add Stock To Exchange  
* Delete Stock From Exchange
* List All Stocks in Exchange

## Technologies used:
* java11
* git
* maven
* h2 database
* spring-boot 2.x
* docker

## 1.Requirements and how to build & run

Requirements change based on preference docker or no-docker, check related subtitle

- jdk11
- maven
- Docker


## 1.1. Docker based approach

change directory to project folder, run commands below

docker build --tag ihse:1.0 .  
docker run --publish 8080:8080 --name ihse ihse:1.0

## 1.2. No-Docker approach

### From IDE

import project into your favourite IDE
open class com.eg.ihse.Application  
IDE should show a play icon at the class name. Click it

### From cmd

change directory to projects folder than run commands below  
mvn package  
java -jar target/ihse-1.0-SNAPSHOT.jar

## 2. How to access/interact with application

By default application uses port 8080  
If you need to change the port:  
- running on host: change the line server.port=8080 in resources/application.properties
- running on docker: modify the docker run --publish command shown in heading 1.1

Sample postman collection is included 

to access h2 web console, from your web browser go to http://localhost:8080/h2-console  
db configuration properties are defined in resources/application.properties  
- JDBC URL => jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
- username => sa
- password => sa

## 3. Package Structure

**controller:** Rest endpoint classes are stored here  
**request/response:** Web Request/Response classes are stored here  
**entity:** @Entity annotated classes are stored here, ORM mapped classes  
**projection:** for read-only SELECT queries use/add classes here - more performant than selecting @Entity annotated classes  
**repo:** JPA/Hibernate query methods are stored here, each @Entity class has its own Repo such as Stock and StockRepo  
**service:** service classes are stored here, operations are done inside these classes  

**src/main/resources/application.properties:** project configuration file  


## 4. Postman Collection

**ihse.postman_collection.json** postman collection for sample requests - using v2.1 

