package com.cmd.hms.patient.test;
 

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cmd.hms.patient.model.Patient;
 
public class PatientTest {
 

    // Set up object
    Long PatientId = 1L;
    String FirstName = "Jon";
    String MiddleName = "Edward";
    String LastName = "Smith";
    Long GenderId = 1L;
    Long TitleId = 1L;

    Date DateOfBirth = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
     String str = formatter.format(DateOfBirth);
    ;

    Patient p = new Patient( PatientId, FirstName, MiddleName, LastName, GenderId, TitleId, DateOfBirth);

    // Tests
    @org.junit.jupiter.api.Test
    public void PatientIdTest(){
        assertTrue(p.getPatientId().equals(PatientId), "PatientId");
    }

    @org.junit.jupiter.api.Test
    public void PatientFirstNameTest(){
        assertTrue(p.getFirstName().equals(FirstName), "FirstName");
    }

    @org.junit.jupiter.api.Test
    public void PatientMiddleNameTest(){
        assertTrue(p.getMiddleName().equals(MiddleName), "MiddleName");
    }

    @org.junit.jupiter.api.Test
    public void PatientLastNameTest(){
        assertTrue(p.getLastName().equals(LastName), "LastName");
    }

    @org.junit.jupiter.api.Test
    public void PatientGenderIdTest(){
        assertTrue(p.getGenderId().equals(GenderId), "GenderId");
    }

    @org.junit.jupiter.api.Test
    public void PatientTitleIdTest(){
        assertTrue(p.getTitleId().equals(TitleId), "TitleId");
    }

    @org.junit.jupiter.api.Test
    public void PatientDateOfBirthTest(){
        assertTrue(p.getDateOfBirth().equals(DateOfBirth), "DateOfBirth");
    }


   
}