package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cmd.hms.patient.model.Patient;
 
public class PatientTest {
 

    // Create empty object
    Patient Patient = new Patient();

    // Tests
    @org.junit.jupiter.api.Test
    public void PatientIdTest(){
        Long PatientId = 1L;
        Patient.setPatientId(PatientId);

        assertTrue(Patient.getPatientId().equals(PatientId), "PatientId");
    }

    @org.junit.jupiter.api.Test
    public void PatientFirstNameTest(){
        String FirstName = "Jon";
        Patient.setFirstName(FirstName);

        assertTrue(Patient.getFirstName().equals(FirstName), "FirstName");
    }

    @org.junit.jupiter.api.Test
    public void PatientMiddleNameTest(){
        String MiddleName = "Edward";
        Patient.setMiddleName(MiddleName);

        assertTrue(Patient.getMiddleName().equals(MiddleName), "MiddleName");
    }

    @org.junit.jupiter.api.Test
    public void PatientLastNameTest(){
        String LastName = "Smith";
        Patient.setLastName(LastName);

        assertTrue(Patient.getLastName().equals(LastName), "LastName");
    }

    @org.junit.jupiter.api.Test
    public void PatientGenderIdTest(){
        Long GenderId = 1L;
        Patient.setGenderId(GenderId);
            
        assertTrue(Patient.getGenderId().equals(GenderId), "GenderId");
    }

    @org.junit.jupiter.api.Test
    public void PatientTitleIdTest(){
        Long TitleId = 1L;
        Patient.setTitleId(TitleId);

        assertTrue(Patient.getTitleId().equals(TitleId), "TitleId");
    }

    @org.junit.jupiter.api.Test
    public void PatientDateOfBirthTest(){
        Date DateOfBirth = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        formatter.format(DateOfBirth);

        Patient.setDateOfBirth(DateOfBirth);

        assertTrue(Patient.getDateOfBirth().equals(DateOfBirth), "DateOfBirth");
    }


   
}