# RESTful service in Spring Boot

This tech- assignment application is developed using spring boot framework and hence the applciation can
be run as a stand alone jar without having to deploy to any servlet container.

Note: All the RESTful service end points within the project are developed as level 2 RESTful service.

## Install and Run

First, download and unzip the source repository from Git:

https://github.com/dineshseralathan/tech-assignment.git

You can either import the unzipped project into eclipse or any IDE of choice or use 
the stand alone jar from  target/test-restapi-0.0.1-SNAPSHOT.jar to run the application

How to run the application as stand alone jar:

Go to the project directory (unzipped and extracted from git source repository) and run the below command to start the server

    java -jar test-restapi-0.0.1-SNAPSHOT.jar

<i>Note</i>: This is all needed to run the application and you are good to test the REST endpoints either using curl or Rest client like chrome extension Advanced Rest client -'ARC'( go to chrome web store and search for ARC and add as chrome extension) 



If you choose to import the project into eclipse (or any IDE) make sure you have maven installed as pre requisite (refer https://maven.apache.org/) in order to pull done runtime dependent jars (via pom.xml)

Right click <b>'TestRestApiApplication.java'</b>, select 'Run as' -> 'Java Application'. This boot straps the application on embedded tomcat servlet container.

Also if you like to create the jar file out of the source code and run as stand alone jar, all you need is to go to projetc directory
and run

       mvn compile  -- to compile
       
 and then run
 
       mvn install  

Above command will build the project, run JUnits, other automated tests ( please check the folder <i>target\surefire-reports</i> for Junit reports) and creates the executable jar

# REST API

The REST APIs from tech-assignment project are described as below.

## Get All Orders

### Request

`GET /orders`

    curl -i -H 'Accept: application/json' http://localhost:8080/orders

### Response

    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8 
    Transfer-Encoding: chunked  
    Date: Sat, 23 Sep 2017 22:52:53 GMT 

    [{"id":1,"productId":"CISLRT","quantity":4,"description":"Cisco Router","status":"Open"},
    {"id":2,"productId":"SMGLRT","quantity":3,"description":"Samsung Router","status":"Canceled"},
    {"id":3,"productId":"LGLRT","quantity":1,"description":"LG Router","status":"In-Transit"},
    {"id":4,"productId":"MTLRT","quantity":2,"description":"Motorola Router","status":"Delivered"}]

## Get a specific Order by Id

### Request

`GET /orders/id`

    curl -i -H 'Accept: application/json' http://localhost:8080/orders/1

### Response

    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Sat, 23 Sep 2017 22:54:07 GMT

    {"id":1,"productId":"CISLRT","quantity":4,"description":"Cisco Router","status":"Open"}


## Create a new Order

### Request

`POST /orders`

      curl -H "Content-Type: application/json" -X POST -d "{\"productId\":\"LVNRT\",\"quantity\":5,\"description\":\"Lenevo Router\",\"status\":\"Open\"}" http://localhost:8080/orders

### Response

    Content-Type: application/json;charset=UTF-8
    Date: Sat, 23 Sep 2017 23:04:14 GMT
    Transfer-Encoding: chunked

    {"id":5,"productId":"LVNRT","quantity":5,"description":"Lenevo Router","status":"Open"}


## Get an order for given status

### Request

`GET /orders/query?status=Open`

    curl -i -H 'Accept: application/json' http://localhost:8080/orders/query?status=Open

### Response

    Content-Type: application/json;charset=UTF-8
    Date: Sat, 23 Sep 2017 23:22:40 GMT
    Transfer-Encoding: chunked

   [{"id":2,"productId":"SMGLRT","quantity":3,"description":"Samsung Router","status":"Canceled"}]



## Get an order for given quantity

### Request

`GET orders/query?quantity=2`

    curl -i -H 'Accept: application/json' http://localhost:8080/orders/query?quantity=2

### Response

    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Sat, 23 Sep 2017 23:24:35 GMT

    [{"id":4,"productId":"MTLRT","quantity":2,"description":"Motorola Router","status":"Delivered"}]

## Get an order for given status and quantity

### Request

`GET /orders/query?status=In-Transit&quantity=1`

    curl -i -H 'Accept: application/json'  http://localhost:8080/orders/query?status=In-Transit&quantity=1

### Response

    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Sat, 23 Sep 2017 23:28:19 GMT

    [{"id":3,"productId":"LGLRT","quantity":1,"description":"LG Router","status":"In-Transit"}]


## Delete an Order 

### Request

`DELETE /order/id`

   curl -i -X DELETE http://localhost:8080/orders/1

### Response

    HTTP/1.1 204
    Date: Sat, 23 Sep 2017 23:18:40 GMT


## Get Fibonacci series for the given number

### Request

`GET /fibonacci/15`

    curl -i -H 'Accept: application/json' http://localhost:8080/fibonacci/15

### Response

    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Sat, 23 Sep 2017 23:37:15 GMT

    [1,1,2,3,5,8,13,21,34,55,89,144,233,377,610]


## Create a dead lock 

### Request

`POST /deadlock`

    curl -H "Content-Type: application/json" -X POST -d "[{\"name\":\"Object 1\"},{\"name\":\"Object 2\"}]" http://localhost:8080/deadlock

### Response

    Content-Type: application/json;charset=UTF-8
    Date: Sun, 24 Sep 2017 00:13:21 GMT
    Transfer-Encoding: chunked

    {"deadlockDetected":true}

<i>Note:</i> Please check the server console (if running from inside IDE) or command line to check the log that has more information on what's happening behind the scene.


## Get unique word count list for given string

### Request

    `GET /wordcount`

        curl -H "Content-Type: application/json" -X POST -d  "{\"tetsing string for unique words and it's respective count!!!\"}"               http://localhost:8080/wordcount

### Response

    Content-Type: application/json;charset=UTF-8
    Date: Sun, 24 Sep 2017 02:11:53 GMT
    Transfer-Encoding: chunked

    {"and":1,"count":1,"for":1,"it's":1,"respective":1,"string":1,"tetsing":1,"unique":1,"words":1}

<b>Note: <i>Couldn't use main method to run for the above class due to the way the classes are packaged inside the spring boot jar, so i exposed REST end point instead<i/> :(</b>
