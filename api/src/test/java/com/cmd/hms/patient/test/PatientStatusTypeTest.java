package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.PatientStatusType;

public class PatientStatusTypeTest extends HttpRequestTest{

     // Instanstiate object
     PatientStatusType PatientStatusType = new PatientStatusType();

     // Tests
     @Test
     public void StatusIdTest(){
         Long StatusId = 1L;
         PatientStatusType.setStatusId(StatusId);
             
         assertTrue(PatientStatusType.getStatusId().equals(StatusId), "StatusId");
     }

     @Test
     public void PatientStatusTest(){
         String Status = "In-treatment";
         PatientStatusType.setStatus(Status);
             
         assertTrue(PatientStatusType.getStatus().equals(Status), "Status");
     }

     @Test
     public void IsDeletedTest(){
         Boolean IsDeleted = false;
         PatientStatusType.setIsDeleted(IsDeleted);
             
         assertTrue(PatientStatusType.getIsDeleted().equals(IsDeleted), "IsDeleted");
     }


    @Test
	public void addPatientStatusType() throws Exception {
		// Add object 
		String Status = "TestStatus";
		
		String getUrl = postPatientStatusType(Status);

		// Get added object
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(Status, getJson.get("Status"));	
	}

    
    
}