
# Personal Medical Records API

  

## Prerequisites

  

1. Git

2. JDK

3. A code editor (e.g. VScode)

4. Apache Maven

5. A database management system (e.g. Postgresql)

6. A client (e.g. Postman)

  

## Set-up

  

1. <strong> Clone the repository </strong>

  

```

git clone https://github.com/RemiBahar/personal-medical-records-api.git

```

  

2. <strong> Open the repository in VSCode </strong>

  

3. <strong> (Optional) Configure the API to work with your preffered database management system. By default, it works with postgresql </strong>

  

Add the database management system driver to pom.xml

```

<!-- Postgresql driver -->

<dependency>

<groupId>org.postgresql</groupId>

<artifactId>postgresql</artifactId>

<scope>runtime</scope>

</dependency>

```

  

Update resources/application.properties to link to the correct database

```

spring.datasource.platform=postgres

spring.datasource.url=jdbc:postgresql://host:port/database

spring.datasource.username=username

spring.datasource.password=password

```

  

4. <strong> Install required packages </strong>

  

```

mvn clean install

```

  

5. <strong> Start the API </strong>

  

```

mvn spring-boot:run

```

  

6. <strong> Run a test query </strong>

  

We will run a GET request to get the OData entity schema

 
```

GET localhost:8080/odata/$metdata

```

  

## Example queries

1. Create a patient 

```
POST localhost:8080/odata/Patients
{

"Name": "John",

"Age": "100"

}
```

2. Update a patient (using PUT)
```
PUT localhost:8080/odata/Patients(1)
{

"Name": "John",

"Age": "25"

}
```

3. Update a patient (using PATCH)
```
PATCH localhost:8080/odata/Patients(1)
{

"Name": "Bruce"

}
```

4. Read a patient (using GET)
```
GET localhost:8080/odata/Patients(1)
```

5. Get all patient records 
```
GET localhost:8080/odata/Patients
```

6. Delete a patient record
```
DELETE localhost:8080/odata/Patients(1)
```