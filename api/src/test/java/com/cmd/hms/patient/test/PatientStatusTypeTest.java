package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.cmd.hms.patient.model.PatientStatusType;

public class PatientStatusTypeTest extends IntegrationTest{

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
		String endPoint = "/PatientStatusTypes";
		add(endPoint, requestBody, adminToken);
	}

    @Test
	public void updateStatus() throws Exception {
		String requestBody = "{\n  \"Status\": \"Updated status\" \n}";
		String endPoint = "/PatientStatusTypes(1)";
		update(endPoint, requestBody, adminToken);
	}

    @Test
	public void invalidUpdateStatus() throws Exception {
		String requestBody = "{\n \"StatusId\": \"10\", \"Status\": \"Invalid update status\" \n}";
		String endPoint = "/PatientStatusTypes(1)";

		assertTrue(invalidUpdateObject(requestBody, endPoint, adminToken));
	}

    @Test
	public void deleteStatus() throws Exception {
        String endPoint = "/PatientStatusTypes(2)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
	}

    @Test
    // Assistance should not be able to READ PatientStatusType
    public void assistanceGetAddressType() throws Exception {
        Boolean request = invalidGet("/PatientStatusTypes", this.assistanceToken);
        assertTrue(request);
    }

    @Test
    // User should not be able to READ PatientStatusType
    public void userGetAddressType() throws Exception {
        Boolean request = invalidGet("/PatientStatusTypes", this.userToken);
        assertTrue(request);
    }
      
}