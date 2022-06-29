package com.cmd.hms.patient.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name="patient")
@SQLDelete(sql = "UPDATE patient SET patient_status = 3 WHERE patient_id=?")
@Where(clause = "patient_status != 3 OR patient_status is null")
public class Patient {
    /** Represents a patient. Is the main class in this API
     * @param PatientId Primary key of patient
     * @param FirstName First name of patient
     * @param LastName Last name of patient
     * @param MiddleName Middle name of patient
     * @param Contacts List of contacts associated with the patient. View this using /Patients(PatientId)/ContactDetails
     * @param PatientStatus Status of patient. Has a bi-directional, one to one mapping with PatientStatusType. 
     * View this using /Patients(PatientId)/PatientStatusTypeDetails. Used for reading patient status only
     * @param PatientStatusId Used for setting patient status to a number. Has foreign key constraints.
     * @param Gender Gender of the patient. Has a uni-directional, one to one mapping with Gender. 
     * View this using /Patients(PatientId)/GenderDetails. Used as getter for gender.
     * @param Gender Used for setting gender to a number with foreign key constraints applied.
    */
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
    @Column(name="patient_id")
    private Long PatientId;

    @Column(name="first_name")
    @NotBlank(message="First name required")
    @Size(max=1000)
    private String FirstName;

    @Column(name="last_name")
    @NotBlank(message="Last name required")
    @Size(max=1000)
    private String LastName;

    @Column(name="middle_name")
    @Size(max=1000)
    private String MiddleName;

    @Column(name="date_of_birth")
    @Past(message="Date of birth must be in the past")
    private Date DateOfBirth;
   
    // Joined fields

    @OneToMany(mappedBy = "Patient") // mappedBy refers to field name in child table pointing to this table
    private List<Contact> Contacts;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="patient_status",insertable = false, updatable = false)
    private PatientStatusType PatientStatus;

    @Column(name="patient_status")
    private Long PatientStatusId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="gender",insertable = false, updatable = false)
    private Gender Gender;

    @Column(name="gender")
    private Long GenderId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="title",insertable = false, updatable = false)
    private Title Title;

    @Column(name="title")
    private Long TitleId;

    @OneToMany(mappedBy = "Patient") // mappedBy refers to field name in child table pointing to this table
    private List<Address> Addresses;

    // Getters and setters
    public Long getPatientId() {
        return PatientId;
    }

    public void setPatientId(Long PatientId) {
        this.PatientId = PatientId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        System.out.print("setting dob");
        this.DateOfBirth = DateOfBirth;
    }

    // 2012-06-01T00:00:00
    // Patient Status

    public PatientStatusType getPatientStatus() {
        return PatientStatus;
      }

    public Long getPatientStatusId() {
        return PatientStatusId;
      }
    
    public void setPatientStatusId(Long PatientStatusId) {
        this.PatientStatusId = PatientStatusId;
      }

    // Gender

    public Gender getGender() {
        return Gender;
      }

      
    public Long getGenderId() {
     return GenderId;
    }

    public void setGenderId(Long GenderId) {
    this.GenderId = GenderId;
    }

    // Title
    public Title getTitle() {
        return Title;
      }

      public Long getTitleId() {
        return TitleId;
      }
    
    public void setTitleId(Long TitleId) {
    this.TitleId = TitleId;
    }
    
}