package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.PatientStatusType;

public class PatientStatusTypeTest {

     // Instanstiate object
     PatientStatusType PatientStatusType = new PatientStatusType();

     // Tests
     @Test
     public void StatusIdTest(){
         Long StatusId = 1L;
         PatientStatusType.setStatusId(StatusId);
             
         assertTrue(PatientStatusType.getStatusId().equals(StatusId), "StatusId");
     }

     @Test
     public void PatientStatusTest(){
         String Status = "In-treatment";
         PatientStatusType.setStatus(Status);
             
         assertTrue(PatientStatusType.getStatus().equals(Status), "Status");
     }

     @Test
     public void IsDeletedTest(){
         Boolean IsDeleted = false;
         PatientStatusType.setIsDeleted(IsDeleted);
             
         assertTrue(PatientStatusType.getIsDeleted().equals(IsDeleted), "IsDeleted");
     }
    
}