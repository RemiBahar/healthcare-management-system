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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.cmd.hms.patient.service.SecurityMethods;

import java.util.Date;

 /** 
  * <ul>
    * <li>Used to represent a Patient entity, the main entity in the API 
    * <li>Generates the patient database table
    * <li>Uses a soft delete to set PatientStatus to 3 when a DELETE request is made
    * <li>Only Patients not deleted (with PatientStatus = 3) are accessible
    * </ul>
*/
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

    

    /** corresponds to first_name column - is required in HTTP request body and must be less than 100 characters
    */
    @Column(name="first_name", length = 100)
    @NotBlank(message="First name required")
    @Size(max=100)
    private String FirstName;

    /** corresponds to last_name column - is required in HTTP request body and must be less than 1000 characters
    */
    @Column(name="last_name", length = 100)
    @NotBlank(message="Last name required")
    @Size(max = 100)
    private String LastName;

    /** corresponds to middle_name column - must be less than 100 characters
    */
    @Column(name="middle_name", length = 100)
    @Size(max=100)
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
    @OrderBy("priority ASC")
    private List<Contact> Contacts;

    /** corresponds to patient_status column - cannot be null, set using PatientStatusId field
    */
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="patient_status",insertable = false, updatable = false)
    private PatientStatusType PatientStatus;

    /** used to set patient_status by passing {PatientStatusId:1} as HTTP request body, the value passed must be a correct foreign key
    */
    @Column(name="patient_status")
    //@NotNull(message = "Patient status is required")
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
    @OrderBy("priority ASC")
    private List<Address> Addresses;

    /* Constructors */
     //Constructor
    public Patient(){}
    
    
    /** Called during /Patients GET request
     * @return      PatientId of patient
    */
    public Long getPatientId() {
        if(PatientId == 2129){
            System.out.println("Patient");
        }
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return PatientId;
        } else{
            return 0L; // error if returning null
        }
    }

    /** 
    * Called when a Patient is added via a POST request. Cannot be set by a user
    * @param PatientId      cannot be null
    */
    public void setPatientId(Long PatientId) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.PatientId = PatientId;
        } 
    }

    /** Called during /Patients GET request
     * @return      FirstName of patient
    */
    public String getFirstName() {
        if(PatientId == 2129){
            System.out.println("Patient");
        }
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return FirstName;
        } else{
            return null;
        }
    }

    /** 
    * Called when FirstName is in the request body of a PUT, POST, or PATCH request
    * @param FirstName      must be under 1000 characters and cannot be null or blank
    */
    public void setFirstName(String FirstName) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.FirstName = FirstName;
        } 
    }

    /** Called during /Patients GET request
    * @return      LastName of patient
    */
    public String getLastName() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return LastName;
        } else{
            return null;
        }
    }

    /** 
    * Called when LastName is in the request body of a PUT, POST, or PATCH request
    * @param LastName       must be under 100 characters and cannot be null or blank
    */
    public void setLastName(String LastName) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.LastName = LastName;
        } 
    }

    /** Called during /Patients GET request
     * @return      MiddleName of patient
    */
    public String getMiddleName() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return MiddleName;
        } else{
            return null;
        }
    }

    /** 
    * Called when MiddleName is in the request body of a PUT, POST, or PATCH request
    * @param MiddleName       must be under 100 characters
    */
    public void setMiddleName(String MiddleName) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.MiddleName = MiddleName;
        } 
    }

    /** Called during /Patients GET request
    * @return      DateOfBirth of patient
    */
    public Date getDateOfBirth() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return DateOfBirth;
        } else{
            return null;
        }
    }

     /** 
    * Called when DateOfBirth is in the request body of a PUT, POST, or PATCH request
    * @param DateOfBirth       must be a datetime in the past and in the format 'yyyy-mm-ddThh:mm:ss' e.g. 1999-04-01T13:30:00
    */
    public void setDateOfBirth(Date DateOfBirth) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.DateOfBirth = DateOfBirth;
        } 
    }

    // Patient Status

    /** Called during /Patients GET request
     * @return      PatientStatus of patient
    */
    public PatientStatusType getPatientStatus() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return PatientStatus;
        } else{
            return null;
        }
      }
    
    /** Called as part of setting PatientStatusId
     * @return      PatientStatusId of patient
    */
    public Long getPatientStatusId() {
        if(PatientId == 2129){
            System.out.println("Patient");
        }
        
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return PatientStatusId;
        } else{
            return null;
        }
      }
    
    /** 
    * Called when PatientStatusId is in the request body of a PUT, POST, or PATCH request
    * @param PatientStatusId       cannot be null and must be a valid foreign key to patient_status
    */
    public void setPatientStatusId(Long PatientStatusId) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.PatientStatusId = PatientStatusId;
        } 
      }

    /** Called during /Patients GET request
     * @return      Gender of patient
    */
    public Gender getGender() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return Gender;
        } else{
            return null;
        }
      }

    /** Called as part of setting GenderId
     * @return      GenderId of patient
    */
    public Long getGenderId() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return GenderId;
        } else{
            return null;
        }
    }

    /** 
    * Called when GenderId is in the request body of a PUT, POST, or PATCH request
    * @param GenderId       must be a valid foreign key to gender
    */
    public void setGenderId(Long GenderId) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.GenderId = GenderId;
        } 
    }

    /** Called during /Patients GET request.
     * Display inline using /Patients?$expand=TitleDetails
     * @return      Title of patient
    */
    public Title getTitle() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return Title;
        } else{
            return null;
        }
      }
    
    /** Called as part of setting TitleId
     * @return      TitleId of patient
    */
    public Long getTitleId() {
        if(new SecurityMethods().viewPatient(PatientId, PatientStatusId)){
            return TitleId;
        } else{
            return null;
        }
      }
    
    /** 
    * Called when TitleId is in the request body of a PUT, POST, or PATCH request
    * @param TitleId       must be a valid foreign key to title
    */
    public void setTitleId(Long TitleId) {
        if(new SecurityMethods().editPatient(PatientStatusId)){
            this.TitleId = TitleId;
        } 
    }
    
}