package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

     // Integration tests
    @Test
	public void addStatus() throws Exception {
		String requestBody = "{\n  \"Status\": \"Add Status\" \n}";
		String endpoint = "/PatientStatusTypes";
		addObject(requestBody, endpoint);
	}

    @Test
	public void updateStatus() throws Exception {
		String requestBody = "{\n  \"Status\": \"Updated status\" \n}";
		String endpoint = "/PatientStatusTypes(1)";
		updateObject(requestBody, endpoint);
	}

    @Test
	public void invalidUpdateStatus() throws Exception {
		String requestBody = "{\n \"StatusId\": \"10\", \"Status\": \"Invalid update status\" \n}";
		String endpoint = "/PatientStatusTypes(1)";
		invalidUpdateObject(requestBody, endpoint);
	}

    @Test
	public void deleteGender() throws Exception {
		String endpoint = "/PatientStatusTypes  (2)";
		deleteObject(endpoint);
	}
      
}