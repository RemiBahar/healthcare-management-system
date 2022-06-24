package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.Contact;
 
public class ContactTest extends HttpRequestTest{
 

    // Instanstiate object
    Contact contact = new Contact();

    // Tests
    @Test
    public void ContactIdTest(){
        Long ContactId = 1L;
        contact.setId(ContactId);
            
        assertTrue(contact.getId().equals(ContactId), "ContactId");
    }

    @Test
    public void ContactNameTest(){
        String Name = "Sue Smith";
        contact.setName(Name);
            
        assertTrue(contact.getName().equals(Name), "Name");
    }

    @Test
    public void ContactTelephoneTest(){
        String Telephone = "01753222444";
        contact.setTelephone(Telephone);
            
        assertTrue(contact.getTelephone().equals(Telephone), "Telephone");
    }

    @Test
    public void ContactMobileTest(){
        String Mobile = "073269441594";
        contact.setMobile(Mobile);
            
        assertTrue(contact.getMobile().equals(Mobile), "Mobile");
    }

    @Test
    public void ContactEmailTest(){
        String Email = "test@hotmail.com";
        contact.setEmail(Email);
            
        assertTrue(contact.getEmail().equals(Email), "Email");
    }

    @Test
    public void ContactPatientIdTest(){
        Long PatientId = 1L; 
        contact.setPatientId(PatientId);
            
        assertTrue(contact.getPatientId().equals(PatientId), "PatientId");
    }

    @Test
    public void ContactTypeIdTest(){
        Long TypeId = 2L;
        contact.setTypeId(TypeId);
            
        assertTrue(contact.getTypeId().equals(TypeId), "TypeId");
    }

    @Test
    public void ContactPriorityTest(){
        Long Priority = 1L;
        contact.setPriority(Priority);
            
        assertTrue(contact.getPriority().equals(Priority), "Priority");
    }

    @Test
	public void addContact() throws Exception {
		// Patient not finish being added unless you call it again here
		// Add object 
		String Name = "John Smith Wick";
		String Telephone = "01628222333";
		String Mobile = "07722244555";
		String Email = "test@hotmail.com";
		String Priority = "1";
		String PatientId = "1";
		String TypeId = "1";

		String data = String.format(
			"{\n  \"Name\": \"%s\", \"Telephone\": \"%s\" \n, \"Mobile\": \"%s\", \"Email\": \"%s\", \"Priority\": \"%s\", \"PatientId\": \"%s\", \"TypeId\": \"%s\"}"
			,Name, Telephone, Mobile, Email, Priority, PatientId, TypeId);
		
		String url = BaseUrl + "/Contacts";
		String getUrl = postObject(data, url);

		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(Name, getJson.get("Name"));
		assertEquals(Telephone, getJson.get("Telephone"));
		assertEquals(Mobile, getJson.get("Mobile"));
		assertEquals(Email, getJson.get("Email"));
		assertEquals(Priority, getJson.get("Priority"));
		assertEquals(PatientId, getJson.get("PatientId"));
		assertEquals(TypeId, getJson.get("TypeId"));
	}
    

}