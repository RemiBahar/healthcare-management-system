package com.cmd.hms.patient.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.security.access.method.P;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientAudId implements Serializable {
   
    private Long Rev;

    private Long PatientId;

    // default constructor

    public PatientAudId(Long Rev, Long PatientId) {
        this.Rev = Rev;
        this.PatientId = PatientId;
    }
    /* 
    public PatientAudId(){

    }
    
    public Long getRev() {
        return Rev;
    }

    public void setRev(Long Rev) {
        this.Rev = Rev;
    }

    public Long getPatientId() {
        return PatientId;
    }

    public void setPatientId(Long PatientId) {
        this.PatientId = PatientId;
    }
    */
    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof PatientAudId)) {
            return false;
        }
        PatientAudId PatientAudId = (PatientAudId) o;
        return Rev == PatientAudId.Rev && PatientId == PatientAudId.PatientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rev, PatientId);
    }
}
