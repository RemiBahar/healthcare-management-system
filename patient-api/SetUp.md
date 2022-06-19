
# Set-Up Procedure

## Prerequisites

1. Git

2. JDK

[Install JDK](https://www.oracle.com/java/technologies/downloads/).

3. A code editor (e.g. VScode)

4. Apache Maven

[Install Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)

5. Postman 

[Intall Postman](https://www.postman.com/downloads/)

  

## Steps

1. Clone the repository. Replace `<branch>` with the database management system you would like to use e.g. main for postgresql or H2. For easy set-up H2 is recommended as it comes with a database with no installation required 

```
git clone --branch <branch> https://github.com/RemiBahar/healthcare-management-system.git
```
  
2. From `/patient-api/api` run `mvn spring-boot:run`

3. From Postman desktop click Collections > Import then import the patient collection `/patient-api/api-test/patient_test_requests`


#### Using H2 branch

4. If you are using H2 you can run the "Get Patients" request to get a list of patients already added in the database. 

#### Using other branches

5. Edit the username, password, and url in `/patient-api/api/src/main/resources/application.properties` to be configured for your database management system

```
spring.datasource.url=jdbc:h2:file:./database/patient_db
spring.datasource.username=admin
spring.datasource.password=password
```

## Setting up a new API

Apart from the code used to define the patient data model in the /model directory, the rest of the code in this API can be reused as is for other APIs.

To use this API's code to build an API to manage a different set of data, do the following:

1. Follow the instructions in the the Set-up section
2. Define a data model in the /model directory. See the Data Model Guide section below for guidance on how the data model works

We will now go through the rest of the code used in this API. Please see the System Architecture section for more detail.

1. [pom.xml](/patient-api/api/pom.xml) is used by maven to manage any dependencies the API has

2. [application.properties](/patient-api/api/src/main/resources/application.properties) is where can configure extra details for our API. In this API we have configured it to connect to a postgresql database called patient using the postgres user. The password is plaintext but should be stored using something like an environment variable for extra security.

3. [JerseyConfig.java](/patient-api/api/src/main/java/com/cmd/hms/patient/config/JerseyConfig.java) is used to set-up our OData service

5. The [service](/patient-api/api/src/main/java/com/cmd/hms/patient/service) directory is used to set-up JPA with the OData service. 

    * [CustomODataJpaProcessor.java](/patient-api/api/src/main/java/com/cmd/hms/patient/service/CustomODataJpaProcessor.java) defines generic readEntitySet, readEntity, createEntity, updateEntity, deleteEntity that can be used for CRUD operations of any entity we define in our data-model. This lets the developer define the data model and have the API interactions and database interactions handled automatically

    * [CustomOdataServiceFactory.java](/patient-api/api/src/main/java/com/cmd/hms/patient/service/CustomODataServiceFactory.java) creates our OData service

    * [ODataJpaServiceFactory.java](/patient-api/api/src/main/java/com/cmd/hms/patient/service/ODataJpaServiceFactory.java) is a wrapper for spring since we are using the Spring-framework

6. [Application.java](/patient-api/api/src/main/java/com/cmd/hms/patient/Application.java) is another wrapper for spring