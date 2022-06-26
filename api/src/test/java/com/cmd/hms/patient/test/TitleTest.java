package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.cmd.hms.patient.model.Title;

public class TitleTest extends HttpRequestTest{

     // Instanstiate object
     Title title = new Title();

     // Tests
     @Test
     public void IdTest(){
         Long Id = 1L;
         title.setTitleId(Id);
             
         assertTrue(title.getTitleId().equals(Id), "Id");
     }

     @Test
     public void TitleMethodTest(){
         String Title = "Mr";
         title.setTitle(Title);
             
         assertTrue(title.getTitle().equals(Title), "Title");
     }
    
     // Integration tests
     @Test
     public void addTitle() throws Exception {
         String requestBody = "{\n  \"Title\": \"Add Title\" \n}";
         String endpoint = "/Titles";
         addObject(requestBody, endpoint);
     }
 
     @Test
     public void updateTitle() throws Exception {
         String requestBody = "{\n  \"Title\": \"Update Title\" \n}";
         String endpoint = "/Titles(1)";
         updateObject(requestBody, endpoint);
     }
 
     @Test
     public void invalidUpdateGender() throws Exception {
         String requestBody = "{\n \"TitleId\": \"10\", \"Title\": \"Invalid title\" \n}";
         String endpoint = "/Titles(1)";
         invalidUpdateObject(requestBody, endpoint);
     }
 
     @Test
     public void deleteTitle() throws Exception {
         String endpoint = "/Titles(2)";
         deleteObject(endpoint);
     }
}