package com.cmd.hms.patient.service;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cmd.hms.patient.model.Patient;

public class SecurityMethods {

    /* Helper methods */

    String authorities;
    Long PatientId;

    public SecurityMethods(){
        // Get JWT best 130 then 186 then 243
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes();

        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        String Authorization = httpServletRequest.getHeader("Authorization");
        String token = StringUtils.substringAfter(Authorization, "Bearer ");

        

        // Decode JWT 
        String[] chunks = token.split("\\.");

        String payload = new String(Base64.getUrlDecoder().decode(chunks[1]));
        
        this.authorities = StringUtils.substringBetween(payload, "Authorities\":[", "]");
        
        String PatientIdString = StringUtils.substringBetween(payload, "PatientId\":", ",");

        if(PatientIdString.equals("null")){
            this.PatientId = null;
        } else {
            this.PatientId = Long.parseLong(PatientIdString);
        }
        
    }
    
    public Boolean hasRole(String findRole){
        //System.out.println(this.authorities.contains(findRole));
        return this.authorities.contains(findRole);
    }

    /* Patient security */

    public Boolean viewPatient(Long patientId, Long patientStatusId){
        return hasRole("ROLE_ADMIN") 
            || hasRole("ROLE_ASSISTANCE") 
            // Patients can view their own records if they are checked or in-treatment
            || (hasRole("ROLE_USER") && this.PatientId == patientId && (patientStatusId == 2 || patientStatusId == 3));

    }

    public Boolean editPatient(Long patientStatusId){
        return hasRole("ROLE_ADMIN")
            // Assistance can edit patient data if the patient is pre-captured or for-deletion
            || (hasRole("ROLE_ASSISTANCE") && (patientStatusId == 1 || patientStatusId == 4)); 
    }

    /* Address security  */
    public Boolean viewAddress(Patient Patient){
        Boolean returnValue = false;

        try {
            returnValue = hasRole("ROLE_ADMIN") 
            || hasRole("ROLE_ASSISTANCE")
            || (hasRole("ROLE_USER") && this.PatientId == Patient.getPatientId() && (Patient.getPatientStatusId() == 2 || Patient.getPatientStatusId() == 3));
        } catch(Exception e){ // If Address not linked to a Patient
            returnValue = hasRole("ROLE_ADMIN") 
                || hasRole("ROLE_ASSISTANCE");
        }

        return returnValue;
    }

    public Boolean editAddress(){
        return hasRole("ROLE_ADMIN") 
            || hasRole("ROLE_ASSISTANCE");
    }

    /* Contact security */

    public Boolean viewContact(Patient Patient){
        Boolean returnValue = false;

        try {
            returnValue = hasRole("ROLE_ADMIN") 
            || hasRole("ROLE_ASSISTANCE")
            || (hasRole("ROLE_USER") && this.PatientId == Patient.getPatientId() && (Patient.getPatientStatusId() == 2 || Patient.getPatientStatusId() == 3));
        } catch(Exception e){ // If Address not linked to a Patient
            returnValue = hasRole("ROLE_ADMIN") 
                || hasRole("ROLE_ASSISTANCE");
        }

        return returnValue;
    }

    public Boolean editContact(Long patientId, Long patientStatusId){
        return hasRole("ROLE_ADMIN") 
            || hasRole("ROLE_ASSISTANCE");
    }

    
}