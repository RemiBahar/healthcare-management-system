package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.ContactType;

public class ContactTypeTest extends HttpRequestTest{

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
        String endpoint = "/ContactTypes";
        addObject(requestBody, endpoint);
    }

    @Test
	public void updateContactType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Different\" \n}";
        String endpoint = "/ContactTypes(1)";

        updateObject(requestBody, endpoint);
	}

    @Test
	public void deleteAddress() throws Exception {
        deleteObject("/ContactTypes(2)");
    }
   
    
}