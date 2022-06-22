package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.Title;

public class TitleTest {

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
    
}