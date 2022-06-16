package com.cmd.hms.patient.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="patient_status")
@SQLDelete(sql = "UPDATE patient_status SET is_deleted = true WHERE status_id=?")
@Where(clause = "is_deleted is false")
public class PatientStatusType {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
    @Column(name="status_id")
    private Long StatusId;

    @Column(name="status")
    private String Status;

    @Column(name="is_deleted")
    private Boolean IsDeleted = false;

    @OneToMany(mappedBy = "PatientStatus") // mappedBy refers to field name in child table pointing to this table
    private List<Patient> Patients;


    // Getters and setters
    public Long getStatusId() {
        return StatusId;
    }

    public void setStatusId(Long StatusId) {
        this.StatusId = StatusId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Boolean getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
}