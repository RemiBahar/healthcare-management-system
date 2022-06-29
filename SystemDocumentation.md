# System Documentation

This document is split into two sections covering the system architecture and source code respectively. 
Please see [javadoc](/api/target/site/apidocs/index.html) for the java documentation of the source code.

# System Architecture

This section is intended to give an overview of the key components of the system

![Patient system architecture](/media/patient-architecture.png)

The following design decisions were made

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



# Source Code Description

This section is intended to give an overview of the source-code and decisions made. For more detail, please see the javadoc.

The Patient API is based on the following ERD:

![ERD of patient API](/media/mvp-erd.png)




## Naming conventions in Database, Data Model, and API

Entities exist in 3 places which each have their own naming conventions:

1. Database. Snake case is used for naming of the database tables and columns, e.g. contact_type. All tables and columns take a singular form e.g. address instead of addresses

2. Data model. Pascal case is used for naming of fields and classes in the Java data model, e.g. the contact_type table in the database is represented by ContactType.java in the data model. All classes and fields take a singular form

3. API. Names for fields are the same as in the data model i.e. singular and pascal case. However the names of entities are plural due to the API using the Apache Olingo Library which adheres to OData standards. For example, the API route for ContactType.java in the data model is /ContactTypes. It is the same as in the data model but with an s added so Address.java is accessed by /Addresss. 


## Data Model Guide

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

# Notes

This section contains important notes about the system

## Package Management

* All dependencies are managed by maven and specified in [pom.xml](/api/pom.xml)

* The following are useful tips

    1. Specify a `<version>` for all dependencies to ensure the application does not break with updates
    2. Some dependencies may be used by dependencies in the configuration, these are transitive dependencies. It is important to find them using `mvn dependency:analyze` and specify a version for them
    3. Use [mvnrepository](https://mvnrepository.com/) to find dependencies

* Apache Olingo relies on `javax` classes and does not support `jakarta` classes. This means this application has to use older versions of dependencies such as spring-boot in some cases. This has may be fixed in a future olingo updata, in which case, the dependencies in the project would need to be updated to the latest version and all javax dependencies and imports would need to be changed to jakarta.





## HTTP Operations

PUT:

By default, olingo uses `UpdateType.PATCH` when updating an entity. So even if a PUT request is made, fields not in the request body won't be set to null.

To set other fields to null there are two options.
1. Include null in the request body, e.g. `{Description: null}`
2. When developing a client use `UpdateType.REPLACE` for `getEntityUpdateRequest()`. See https://olingo.apache.org/doc/odata4/tutorials/od4_basic_client_read.html 


## Proposed extensions

1. Increasing performance
    *  Currently this application uses a blocking, multi-threaded approach. Performance can be improved by switching to a non-blocking, event-driven, and asynchronous approach. One way of doing this is to switch to the [reactive stack](https://spring.io/reactive) from spring-boot instead of the currently used [servlet stack](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html)

    * Kubernetes can be used for load-balancing

    * Client caching can be used for increased performance, though this is something that could be handled by a front-end service e.g. an app working with this application as a back-end service
