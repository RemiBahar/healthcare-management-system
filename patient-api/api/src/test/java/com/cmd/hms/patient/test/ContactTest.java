package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.Contact;
 
public class ContactTest {
 

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

    

}