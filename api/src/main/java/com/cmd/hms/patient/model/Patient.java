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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name="patient")
@SQLDelete(sql = "UPDATE patient SET patient_status = 3 WHERE patient_id=?")
@Where(clause = "patient_status != 3 OR patient_status = null")
public class Patient {
    // Fields
    /** corresponds to auto-incremented patient_id primary key column
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
    @Column(name="patient_id")
    private Long PatientId;

    /** corresponds to first_name column - is required in HTTP request body and must be less than 1000 characters
    */
    @Column(name="first_name")
    @NotBlank(message="First name required")
    @Size(max=1000)
    private String FirstName;

    /** corresponds to last_name column - is required in HTTP request body and must be less than 1000 characters
    */
    @Column(name="last_name")
    @NotBlank(message="Last name required")
    @Size(max=1000)
    private String LastName;

    /** corresponds to middle_name column - must be less than 1000 characters
    */
    @Column(name="middle_name")
    @Size(max=1000)
    private String MiddleName;

    /** corresponds to date_of_birth column - must be a datetime in the past and in the format 'yyyy-mm-ddThh:mm:ss'
    */
    @Column(name="date_of_birth")
    @Past(message="Date of birth must be in the past")
    private Date DateOfBirth;
   
    // Joined fields

    /** list of a patient's contacts - accessed by /Patient(ID)/ContactDetails
    */
    @OneToMany(mappedBy = "Patient") // mappedBy refers to field name in child table pointing to this table
    private List<Contact> Contacts;

    /** corresponds to patient_status column - cannot be null, set using PatientStatusId field
    */
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="patient_status",insertable = false, updatable = false)
    private PatientStatusType PatientStatus;

    /** used to set patient_status by passing {PatientStatusId:1} as HTTP request body, the value passed must be a correct foreign key
    */
    @Column(name="patient_status")
    @NotNull(message = "Patient status is required")
    private Long PatientStatusId;

    /** corresponds to gender column - set using GenderId field
    */
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="gender",insertable = false, updatable = false)
    private Gender Gender;

    /** used to set gender by passing {GenderId:1} as HTTP request body, the value passed must be a correct foreign-key
    */
    @Column(name="gender")
    private Long GenderId;

    /** corresponds to title column - set using TitleId field
    */
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="title",insertable = false, updatable = false)
    private Title Title;

    /** used to set title by passing {TitleId:1} as HTTP request body, the value passed must be a correct foreign-key
    */
    @Column(name="title")
    private Long TitleId;

    /** list of a patient's addresses - accessed by /Patient(ID)/AddressDetails
    */
    @OneToMany(mappedBy = "Patient") 
    private List<Address> Addresses;
    
    /** Called during /Patients GET request
     * @return      PatientId of patient
    */
    public Long getPatientId() {
        return PatientId;
    }

    /** 
    * Called when a Patient is added via a POST request. Cannot be set by a user
    * @param PatientId      cannot be null
    */
    public void setPatientId(Long PatientId) {
        this.PatientId = PatientId;
    }

    /** Called during /Patients GET request
     * @return      FirstName of patient
    */
    public String getFirstName() {
        return FirstName;
    }

    /** 
    * Called when FirstName is in the request body of a PUT, POST, or PATCH request
    * @param FirstName      must be under 1000 characters and cannot be null or blank
    */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /** Called during /Patients GET request
    * @return      LastName of patient
    */
    public String getLastName() {
        return LastName;
    }

    /** 
    * Called when LastName is in the request body of a PUT, POST, or PATCH request
    * @param LastName       must be under 1000 characters and cannot be null or blank
    */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /** Called during /Patients GET request
     * @return      MiddleName of patient
    */
    public String getMiddleName() {
        return MiddleName;
    }

    /** 
    * Called when MiddleName is in the request body of a PUT, POST, or PATCH request
    * @param MiddleName       must be under 1000 characters
    */
    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    /** Called during /Patients GET request
    * @return      DateOfBirth of patient
    */
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

     /** 
    * Called when DateOfBirth is in the request body of a PUT, POST, or PATCH request
    * @param DateOfBirth       must be a datetime in the past and in the format 'yyyy-mm-ddThh:mm:ss' e.g. 1999-04-01T13:30:00
    */
    public void setDateOfBirth(Date DateOfBirth) {
        System.out.print("setting dob");
        this.DateOfBirth = DateOfBirth;
    }

    // Patient Status

    /** Called during /Patients GET request
     * @return      PatientStatus of patient
    */
    public PatientStatusType getPatientStatus() {
        return PatientStatus;
      }
    
    /** Called as part of setting PatientStatusId
     * @return      PatientStatusId of patient
    */
    public Long getPatientStatusId() {
        return PatientStatusId;
      }
    
    /** 
    * Called when PatientStatusId is in the request body of a PUT, POST, or PATCH request
    * @param PatientStatusId       cannot be null and must be a valid foreign key to patient_status
    */
    public void setPatientStatusId(Long PatientStatusId) {
        this.PatientStatusId = PatientStatusId;
      }

    /** Called during /Patients GET request
     * @return      Gender of patient
    */
    public Gender getGender() {
        return Gender;
      }

    /** Called as part of setting GenderId
     * @return      GenderId of patient
    */
    public Long getGenderId() {
     return GenderId;
    }

    /** 
    * Called when GenderId is in the request body of a PUT, POST, or PATCH request
    * @param GenderId       must be a valid foreign key to gender
    */
    public void setGenderId(Long GenderId) {
    this.GenderId = GenderId;
    }

    /** Called during /Patients GET request.
     * Display inline using /Patients?$expand=TitleDetails
     * @return      Title of patient
    */
    public Title getTitle() {
        return Title;
      }
    
    /** Called as part of setting TitleId
     * @return      TitleId of patient
    */
    public Long getTitleId() {
        return TitleId;
      }
    
    /** 
    * Called when TitleId is in the request body of a PUT, POST, or PATCH request
    * @param TitleId       must be a valid foreign key to title
    */
    public void setTitleId(Long TitleId) {
    this.TitleId = TitleId;

    }
    
}