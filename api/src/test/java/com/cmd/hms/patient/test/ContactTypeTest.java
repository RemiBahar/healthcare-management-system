package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.ContactType;

public class ContactTypeTest extends IntegrationTest{

     // Instanstiate object
     ContactType contactType = new ContactType();

     // Tests
     @Test
     public void ContactTypeIdTest(){
         Long TypeId = 1L;
         contactType.setContactTypeId(TypeId);
             
         assertTrue(contactType.getContactTypeId().equals(TypeId), "TypeId");
     }

     @Test
     public void TitleTest(){
         String Title = "Family";
         contactType.setTitle(Title);
             
         assertTrue(contactType.getTitle().equals(Title), "Title");
     }

    @Test
	public void addContactType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Something\" \n}";
        String endPoint = "/ContactTypes";
        add(endPoint, requestBody, this.adminToken);
    }

    @Test
	public void updateContactType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Different\" \n}";
        String endPoint = "/ContactTypes(1)";

        update(endPoint, requestBody, this.adminToken);
	}

    @Test
	public void deleteCotactType() throws Exception {
        String endPoint = "/ContactTypes(2)";
        String url = this.baseUrl + endPoint;
         
        assertFalse(delete(url, this.adminToken));
    }
   
    @Test
     // Assistance should not be able to READ ContactType
     public void assistanceGetContactType() throws Exception {
         Boolean request = invalidGet("/ContactTypes", this.assistanceToken);
         assertTrue(request);
     }
 
    @Test
    // User should not be able to READ ContactType
    public void userGetContactType() throws Exception {
        Boolean request = invalidGet("/ContactTypes", this.userToken);
        assertTrue(request);
    }
}