package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"01628222333\" \n, \"Mobile\": \"07722244555\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"PatientId\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Contacts";
        addObject(requestBody, endpoint);
	}

    
    @Test
	public void minimalAddContact() throws Exception {
        /*
         * Update this according to what fields are mandatory according to requirements
         */
		String requestBody = "{\n  \"Name\": \"Patrick Alan Smith\", \"Mobile\": \"023884828429\"}";
        String endpoint = "/Contacts";
        addObject(requestBody, endpoint);
	}
    
    
    @Test
	public void invalidContactPatient() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"01628222333\" \n, \"Mobile\": \"07722244555\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"PatientId\": \"10\", \"TypeId\": \"1\"}";
        String endpoint = "/Contacts(2)";
        invalidUpdateObject(requestBody, endpoint);
	}

    @Test
	public void invalidContactType() throws Exception {
		String requestBody = "{\n  \"Name\": \"Bobby John Jones\", \"TypeId\": \"1\"}";
        String endpoint = "/Contacts(2)";
        invalidUpdateObject(requestBody, endpoint);
	}

    @Test
	public void deleteContact() throws Exception {
        deleteObject("/Contacts(2)");
    }

}