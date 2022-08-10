package com.cmd.hms.patient.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.cmd.hms.patient.service.SecurityMethods;

 /**Used to represent a Contact, stored in the address table and accessible via /Contacts. Linked to a Patient on a many-to-one basis.
*/
@Entity
@Audited
public class Contact {
  /** Corresponds to auto-incremented, contact_id primary key
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
  @Column(name="contact_id")
  private Long Id;

  /** Corresponds to name, if NULL then it is assumed that this contact refers to the Patient's personal contact details, max length 1000 characters
   */
  @Column(name="name", length = 1000)
  @Size(max = 1000)
  private String Name;

  /** Corresponds to telephone, must consist of 5-15 digits optionally preceded by + and a country code
   * 
   */
  @Column(name="telephone", length = 20)
  @Pattern(message = "Invalid telephone", regexp="(?:[+]{1}[0-9]{2})?[0-9]{5,15}")
  @Size(max = 20)
  private String Telephone;

  /** Corresponds to mobile, must consist of 5-15 digits optionally preceded by + and a country code
   * 
   */
  @Column(name="mobile", length = 20)
  @Pattern(message =  "Invalid mobile", regexp="(?:[+]{1}[0-9]{2})?[0-9]{5,15}")
  @Size(max = 20)
  private String Mobile;

  /** Corresponds to email, must be a valid email
   * 
   */
  @Column(name="email", length = 100)
  @Email(message="Must be email")
  @Size(max = 100)
  private String Email;
  
  /** Patient the Contact is linked to
   * 
   */
  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="patient",insertable = false, updatable = false)
  private Patient Patient;

  /** Used for linking a Contact to a Patient via the PatientId foreign-key, cannot be NULL
   * 
   */
  @Column(name="patient")
  @NotNull(message = "Contact must be linked to a patient")
  private Long PatientId;

  /** type of the contact
   * 
   */
  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="type",insertable = false, updatable = false)
  @NotAudited
  private ContactType Type;

  /** Used for linking a Contact to a ContactType via the TypeId foreign-key
   * 
   */
  @Column(name="type")
  private Long TypeId;

  /** Corresponds to priority, used to order a Patient's list of contacts in order of preference
   * 
   */
  @Column(name="priority")
  @Positive
  private Long Priority;

  /* Constructor */
  public Contact(){}



  // Getters and setters

  /** Used to get the Id of a Contact in a GET request
   * 
   * @return Id
   */
  public Long getId() {
    if(new SecurityMethods().viewContact(Patient)){
      return Id;
    } else{
        return 0L;
    }
  }

  /** Used to set the Id of a Contact in a POST/PUT/PATCH request
   * 
   * @param Id - set automatically, not by the user
   */
  public void setId(Long Id) {
    if(new SecurityMethods().editAddress()){
      this.Id = Id;
    } 
  }

  /** Used to get the Name of a Contact in a GET request
   * 
   * @return Name
   */
  public String getName() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Name;
    } else{
        return null;
    }
  }

    /** Used to set the Name of a Contact in a POST/PUT/PATCH request
   * 
   * @param Name - full name of the Contact or NULL if Contact refers to Patient's personal contact details
   */
  public void setName(String Name) {
    if(new SecurityMethods().editAddress()){
      this.Name = Name;
    } 
  }

  /** Used to get the Telephone of a Contact in a GET request
   * 
   * @return Telephone
   */
  public String getTelephone() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Telephone;
    } else{
        return null;
    }
  }

  public void setTelephone(String Telephone) {
    if(new SecurityMethods().editAddress()){
      this.Telephone = Telephone;
    } 
  }

  /** Used to get the Mobile of a Contact in a GET request
   * 
   * @return Mobile
   */
  public String getMobile() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Mobile;
    } else{
        return null;
    }
  }

  /** Used to set the Mobile of a Contact in a POST/PUT/PATCH request
   * 
   * @param Mobile - must be 5-10 digits preceded optionally by a + and 2-digit country code
   */
  public void setMobile(String Mobile) {
    if(new SecurityMethods().editAddress()){
      this.Mobile = Mobile;
    } 
  }

  /** Used to get the Email of a Contact in a GET request
   * 
   * @return Email
   */
  public String getEmail() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Email;
    } else{
        return null;
    }
  }

  /** Used to set the Email of a Contact in a POST/PUT/PATCH request
   * 
   * @param Email - must be a valid email
   */
  public void setEmail(String Email) {
    if(new SecurityMethods().editAddress()){
      this.Email = Email;
    } 
  }

  /** Used to get the Patient the Contact corresponds to in a GET request
   * 
   * @return Patient - more information can be shown using /Contacts(1)/PatientDetails
   */
  public Patient getPatient() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Patient;
    } else{
        return null;
    }
  }

  /** Used to set the Patient of a Contact
   * 
   * @param Patient - use setPatientId instead
   */
  public void setPatient(Patient Patient) {
    if(new SecurityMethods().editAddress()){
      this.Patient = Patient;
    } 
  }

  /** Used to get the PatientId of the Patient the Contact corresponds to
   * 
   * @return Id
   */
  public Long getPatientId() {
    if(new SecurityMethods().viewAddress(Patient)){
      return PatientId;
    } else{
        return null;
    }
  }

  /** Used to set the PatientId of the Patient a Contact corresponds to in a POST/PUT/PATCH request
   * 
   * @param PatientId - foreign key to Patient
   */
  public void setPatientId(Long PatientId) {
    if(new SecurityMethods().editAddress()){
      this.PatientId = PatientId;
    } 
  }

  /** Used to get the Priority of a Contact in a GET request
   * 
   * @return Priority
   */
  public Long getPriority() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Priority;
    } else{
        return null;
    }
  }

  /** Used to set the Priority of a Contact in a POST/PUT/PATCH request
   * 
   * @param Priority - must be a positive integer
   */
  public void setPriority(Long Priority) {
    if(new SecurityMethods().editAddress()){
      this.Priority = Priority;
    } 
  }
  
 // Join getters and setters

 /** Used to get the ContactType of a Contact in a GET request
   * 
   * @return Id
   */
 public ContactType getType() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Type;
    } else{
        return null;
    }
  }

  /** Used to get the TypeId of the ContactType of a Contact in a GET request
   * 
   * @return Id
   */
  public Long getTypeId() {
    if(new SecurityMethods().viewAddress(Patient)){
      return TypeId;
    } else{
        return null;
    }
  }

    /** Used to set the TypeId of the ContactType of the Contact in a POST/PUT/PATCH request
   * 
   * @param TypeId - foreign key to ContactType
   */
  public void setTypeId(Long TypeId) {
    if(new SecurityMethods().editAddress()){
      this.TypeId = TypeId;
    } 
  }
}