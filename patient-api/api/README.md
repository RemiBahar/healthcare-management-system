
# Patient API

## Source Code Description


This section is intended to give an overview of the source-code and decisions made. For more detail, please see the javadoc.

The Patient API is based on the following ERD:

![ERD of patient API](/media/mvp-erd.png)

### Naming conventions in Database, Data Model, and API

Entities exist in 3 places which each have their own naming conventions:

1. Database. Snake case is used for naming of the database tables and columns, e.g. contact_type. All tables and columns take a singular form e.g. address instead of addresses

2. Data model. Pascal case is used for naming of fields and classes in the Java data model, e.g. the contact_type table in the database is represented by ContactType.java in the data model. All classes and fields take a singular form

3. API. Names for fields are the same as in the data model i.e. singular and pascal case. However the names of entities are plural due to the API using the Apache Olingo Library which adheres to OData standards. For example, the API route for ContactType.java in the data model is /ContactTypes. It is the same as in the data model but with an s added so Address.java is accessed by /Addresss. 




  

## Prerequisites

  

1. Git

2. JDK

3. A code editor (e.g. VScode)

4. Apache Maven

5. A database management system (e.g. Postgresql)

6. A client (e.g. Postman)

  

## Set-up

1. Install the pre-requistes 

* Git 
* Java


* (Optional) VSCode
* (Optional) Java extension for VSCode

1. Clone the repository. Replace `<branch>` with the database management system you would like to use e.g. main for postgresql or H2. For easy set-up H2 is recommended as it comes with a database with no installation required 

```
git clone --branch <branch> https://github.com/RemiBahar/healthcare-management-system.git
```
  
2. <strong> Open the repository in VSCode </strong>