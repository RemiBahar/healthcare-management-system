package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.cmd.hms.patient.model.ContactType;

public class ContactTypeTest{

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

   
    
}