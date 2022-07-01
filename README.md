
# Patient API

## Source Code Description

The system is based on the following architecture.

![Patient system architecture](/media/patient-architecture.png)

The following design decisions were made:

1. <b>Embedded application server (Tomcat)</b>. 
* By being embedded, the patient microservice can be self-contained. Starting the microservice also starts the embedded server
* This provides easier deployment and better isolation

2. <b> Olingo </b> 
* Automatically creates api endpoints based on OData using the classes defined in the data model
* Implements OData, sending OData responses and allowing for OData requests

3. <b> JPA </b>
* Allows the database to be defined by the data model which includes getters, setters, joins, and more
* Allows for interchangeable database management systems

4. <b> Maven </b>
* Makes management of the service easier using `mvn clean test` to test the application, `mvn clean package` to package the application into a JAR for docker, and `mvn spring-boot:run` to start the service
* Allows dependency versions to be specified to increase long-term reliability
* Allows dependencies to be analysed for vulnerabilities

5. <b> Spring-Boot </b>
* Provides many features, such as automated integration (against api) and unit tests (against data model) run with `mvn clean test`
* Allows profiles to be defined using different databases, e.g. embedded H2 for testing

6. <b> Docker (optional) </b>
* Increaes isolation of the application from the host and can make deployment easier 


Please see [SourceCode.md](SourceCode.md) for details on the source code. There is also a [javadoc](https://htmlpreview.github.io/?https://github.com/RemiBahar/patient/blob/main/api/target/site/apidocs/index.html).


## Set-up procedure

* The system can be set-up with or without Docker
* Different profiles in spring and the use of JPA allow the system to be run with different databases. Currently H2 and Postgresql have been added as profiles

Please see [SetUp.md](SetUp.md) for more detail


## Testing

1. Unit testing has been implemented testing the getters and setters of the data model
2. Integration testing has been implementing testing the following, for every entity:
    * Adding an entity with every field filled in
    * Adding an entity with only the required fields filled in
    * Updating an entity
    * Updating an entity with invalid foreign keys
    * Updating an entity and testing the field validaton
    * Deleting an entity
3. Performance testing has been implemented using Gatling
    * Up to 1500 users are simulated using the system and sending get and post requests 

Please see [Testing.md](Testing.md)