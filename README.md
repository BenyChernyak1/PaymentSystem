# Payment System
Implementation of peer to peer distributed payment system that can be integrated into one of the products.

## User Story

As a customer, I'd like to pay other customers for various services or products I buy from them.

## Prerequisite
* Consider Docker is installed.  
* Consider *docker-compose* tool is installed as well.
* Java 8
* Gradle 6.8.3

## How to run
* Run *docker-compose up* command from the project directory.
* Execute the spring-boot application by running *PaymentApplication* file
* Open a browser on *http://localhost:8080/swagger-ui.html#/payments-controller-impl/createPaymentUsingPOST*
* Click **Try it out**
* Set up a values for Payment Entity JSON model (*id* is optional, no need to set up a value for it, it would be generated before saving into the DB).
* Execute the REST call.

## Check the DB
* In this solution *MariaDB* was used as a database (*InnoDB* as an engine).
* Use any client with following connection details:
  * Network type: MariaDB or MySQL
  * hostname/IP:  127.0.0.1
  * user:         value of *spring.datasource.username* in *application.properties* file
  * password:     value of *spring.datasource.password* in *application.properties* file
  * port:         3306 
* Check the DB table *PAYMENT* for data.
