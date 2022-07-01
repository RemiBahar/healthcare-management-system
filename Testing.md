# Testing Guide

This document is split into two sections covering the testing done using code (unit, integration, and performance testing) and using postman to
interact with the API.

For more information, please see the [Testing Javadoc](https://htmlpreview.github.io/?https://github.com/RemiBahar/patient/blob/main/api/target/site/testapidocs/index.html).

# Unit and Integration tests

* For increased flexibility and more advanced testing, test cases for the application were coded instead of using postman.
* All tests are in the [test directory](/api/src/test/java/com/cmd/hms/patient/test/). Tests are split based on the class they refer to with unit tests appearing first followed by integration tests
* Tests rely on generic methods defined in [HTttpRequestTest](/api/src/test/java/com/cmd/hms/patient/test/HttpRequestTest.java). 
    * This class also sets tests to use an in-memory postgres [database](/api/src/main/resources/application-test.properties) that is populated and cleared in each test-run
    * This class sets the server used in testing to run on port 8079 so as to not interfere with services using other ports
* Some initial data is inserted into the test database prior to testing using [test-data.sql](/api/src/main/resources//test-data.sql). 
    * Tests are run in a random but deterministic order. Inserting data first stops errors where a test for deleting a patient is run before a test to update that patient. This stops the order of tests having to be manually defined using `@Order`
    * This script works with the in-memory postgresql database used for testing. If you want more database abstraction, a database migration tool such as [liquibase](https://www.liquibase.org/) could be used

## Unit testing

We will be going through the unit tests for the core Patient class.

Junit is imported for unit tests as well as a date type and the Patient class.

```
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cmd.hms.patient.model.Patient;
```

The `HttpRequestTest` which provides standard methods for integration tests is extended (more on that in the next section)

```
public class PatientTest extends HttpRequestTest{
```

We can now write a simple unit test to get and set the patient ID

```
@org.junit.jupiter.api.Test
public void PatientIdTest(){
    Long PatientId = 1L;
    Patient.setPatientId(PatientId);

    assertTrue(Patient.getPatientId().equals(PatientId), "PatientId");
}
```

## Integration testing

In general the following integration tests were added for each class:
1. <b> Adding a valid record with all fields filled in </b>

This uses the `addObject(String requestBody, String endpoint)` method from `HttpRequestTest` to peform a test for adding a patient.

This method does the following
* Sends a POST request to the provided endpoint with the provided JSON body
* Gets the ID of the created object and sends a GET request to get the JSON of the added object
* Compares every field in the request JSON with the added object JSON to check if they're equal

```
@Test
public void addPatient() throws Exception {
    String requestBody = "{\n  \"FirstName\": \"Charles\", \"MiddleName\": \"Francis\" \n, \"LastName\": \"Xaiver\", \"PatientStatusId\": \"1\", \"GenderId\": \"1\", \"TitleId\": \"1\", \"DateOfBirth\": \"1999-04-01T04:00:00\"}";
    String endpoint =  "/Patients";
    addObject(requestBody, endpoint);
}
```

2. <b> Adding a record with the minimum possible fields filled in  </b>

This is the same process for the previous test but with less fields filled in for the patient object, e.g.

```
String requestBody = "{\n  \"FirstName\": \"Charles\", \"LastName\": \"Xaiver\"}";
```

3. <b> Attempting to set a foreign key to an invalid value </b>

This uses the `invalidUpdateObject(String requestBody, String endpoint)` method from `HttpRequestTest` to peform a test for setting a patient foreign key to an invalid value.

This method does the following:
* Uses `endpoint` to send a GET request to get the initial object
* Uses the invalid `requestBody` and `endpoint` to send a PATCH request to update the object
* Uses `endpoint` to send a GET request to get the final object
* Compares the JSON of the initial and final object which should be equal as if one of the fields is invalid the whole update should be stopped without changing anything
* This same method could also be used for more negative testing such as testing validation, e.g. first name cannot be blank

```
@Test
	public void invalidPatientGender() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"GenderId\": \"10\"}";
		String endpoint = "/Patients(1)";
		invalidUpdateObject(requestBody, endpoint);
	}
```

4. <b> Sending a valid update request </b>

This uses the `updateObject(String requestBody, String endpoint)` method from `HttpRequestTest` to peform a test for updating a patient.

This method does the following:
* Send GET request to `endpoint` to get initial object
* Send PATCH request to `endpoint` with `requestBody` to update the object
* Send GET request to `endpoint` to get updated object
* Parses updated object JSON. Fields included in the `requestBody` should be equal to their corresponding fields in the updated object. Fields not included in the
`requestBody` should be unchanged and equal to their corresponding fields in the initial object
* Dates are sent as a string but returned as a `Date(timestamp)` object so some extra conversion is included in the method to check for equal dates

```
@Test
public void updatePatient() throws Exception {
    String requestBody = "{\n  \"FirstName\": \"Alan\", \"MiddleName\": \"Mathison\" \n, \"LastName\": \"Turing\", \"DateOfBirth\": \"1920-04-01T04:00:00\"}";
    String endpoint = "/Patients(1)";
    updateObject(requestBody, endpoint);
}
```

5. <b> Deleting a record </b>
 
This uses the `deleteObject(String endpoint)` method from `HttpRequestTest` to peform a test for deleting a patient. 

This method does the following:
* Sends DELETE request to `endpoint`
* Sends GET request to `endpoint`. This should return an error if the object is deleted 
* This method can be used for both deletes and soft-deletes where the object is deleted by setting a flag such as `is_deleted` to `true`. 
In either case after deletion the object should not be accessible with a GET request

## Performance testing

Performance testing is planned to be done using JMeter.


# Postman

To view and run sample requests for the patient service, please use postman.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/21158677-89e8a063-1378-4575-8eb2-9ae890ae703d?action=collection%2Ffork&collection-url=entityId%3D21158677-89e8a063-1378-4575-8eb2-9ae890ae703d%26entityType%3Dcollection%26workspaceId%3D73a14224-799e-44dd-81c8-0c1296576d59)