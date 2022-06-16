
# Patient API


## Source Code Description


This section is intended to give an overview of the source-code and decisions made. For more detail, please see the javadoc.

The Patient API is based on the following ERD:

![ERD of patient API](/patient-api/media/mvp-erd.png)


### System Architecture

* Maven used for package-management 
* Spring-framework used especially JPA as an ORM to handle database interactions 
* Postgresql database used. Though by using JPA the database management system is interchangeable
* Apache Olingo used to ensure the API adheres to OData standards. Also automatically configures API routes


### Naming conventions in Database, Data Model, and API

Entities exist in 3 places which each have their own naming conventions:

1. Database. Snake case is used for naming of the database tables and columns, e.g. contact_type. All tables and columns take a singular form e.g. address instead of addresses

2. Data model. Pascal case is used for naming of fields and classes in the Java data model, e.g. the contact_type table in the database is represented by ContactType.java in the data model. All classes and fields take a singular form

3. API. Names for fields are the same as in the data model i.e. singular and pascal case. However the names of entities are plural due to the API using the Apache Olingo Library which adheres to OData standards. For example, the API route for ContactType.java in the data model is /ContactTypes. It is the same as in the data model but with an s added so Address.java is accessed by /Addresss. 






### Data Model Guide

The Java Persistance API (JPA) is used as the ORM for this API. It allows the developer to define a data model consisting of classes and automatically creates the database and handles database interactions from the API. A lot of `@` symbols appear in the code. These are called annotations and used to configure the data model.

We will be going through the main class in this API, [Patient.java](/patient-api/api/src/main/java/com/cmd/hms/patient/model/Patient.java).

Classes always start with `@Entity` to make JPA aware of the class. `@Table(name="patient")` is used to set the name of the database table.

```
@Entity
@Table(name="patient")
public class Patient {...
```

We then define the primary key of the table. `@Id` is used to identify the field as the primary key in the table. `@GeneratedValue(strategy = GenerationType.IDENTITY)`is used to configure ID's to be auto-incremented for this table only. By default JPA auto-increments primary keys across all tables. `@Column(name="patient_id")` is used to identify this field as a database column and set its name to patient_id. The datatype of the database column will be based on the datatype of the field so by using `Long`, the database column is set to bigint. 

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
@Column(name="patient_id")
private Long PatientId;
```

For every standard (i.e. not a foreign key) column in the database table we need to define the field, a set method for writing to the database and get method for reading from the database. JPA automatically detects the get and set methods for a field but we have to ensure for a field named X the get method is `getX` and the set method is `setX`

```
@Column(name="first_name")
private String FirstName;

public void setFirstName(String FirstName) {
    this.FirstName = FirstName;
}

public String getLastName() {
    return LastName;
}
```

For joined fields this is slightly different. Consider a one-to-many relationship first where one patient has a status but a status can be linked to multiple patients.

The field is defined as follows. We have defined two fields, the first called `Gender` is only used to link a patient to their gender and for read operations. The second, called `GenderId` is used for setting the patient's gender. This way we can easily set the gender of a patient by passing in the foreign-key, e.g. to set a patient's gender to male we use `"GenderId": "1"` in our request body. JPA automatically sets up foreign key constraints so we cannot pass in an invalid foreign-key.

This next bit is slightly more advanced. `@ManyToOne` is used to specify that many patients can be linked to one gender. `CascadeType.MERGE` is used because we have seperate auto-incrementing ID's for each table. `FetchType.LAZY` is used to load joined data when needed instead of straight away. This reduces the resource-usage of the API. We set the first field `Gender` to be read-only using `insertable = false, updatable = false`. We must do this as only one field can be used to write to the column. 

```
@ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
@JoinColumn(name="gender",insertable = false, updatable = false)
private Gender Gender;

@Column(name="gender")
private Long GenderId;

```

We then have the following set and get methods for a patient's gender. The `Gender` only has a get method since it is read only and the `GenderId` has a set and get method. 

```
public Gender getGender() {
    return Gender;
    }

    
public Long getGenderId() {
    return GenderId;
}

public void setGenderId(Long GenderId) {
this.GenderId = GenderId;
}
```
Currently we have specified the many to one relation between patient and gender. If we want to we can specify the one to many relationship between gender and patient. Another example of this is when we have linked many addresses to one patient. We specify `@OneToMany(mappedBy = "Gender")` where `Gender` is the name of the gender field in Patient.java. This allows us to get patients by gender using /Genders(1)/PatientDetails - more will be covered on this in the OData section. In the actual code for this API we have not specified a reference to Patient in Gender because this allows the same Gender class to be reused for multiple entities.

```
@OneToMany(mappedBy = "Gender") // mappedBy refers to field name in child table pointing to this table
private List<Patient> Patients;
```

### Setting up a new API

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

### Set-up
  

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