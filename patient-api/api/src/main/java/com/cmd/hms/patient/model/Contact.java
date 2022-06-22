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

import jakarta.validation.constraints.NotBlank;


@Entity
public class Contact {
  //Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
  @Column(name="contact_id")
  private Long Id;

  @Column(name="name")
  private String Name;

  @Column(name="telephone")
  private String Telephone;

  @Column(name="mobile")
  private String Mobile;

  @Column(name="email")
  @NotBlank
  private String Email;
  
  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="patient",insertable = false, updatable = false)
  private Patient Patient;

  @Column(name="patient")
  private Long PatientId;

  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="type",insertable = false, updatable = false)
  private ContactType Type;

  @Column(name="type")
  private Long TypeId;

  @Column(name="priority")
  private Long Priority;



  // Getters and setters
  public Long getId() {
    return Id;
  }

  public void setId(Long Id) {
    this.Id = Id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String Name) {
    this.Name = Name;
  }

  public String getTelephone() {
    return Telephone;
  }

  public void setTelephone(String Telephone) {
    this.Telephone = Telephone;
  }

  public String getMobile() {
    return Mobile;
  }

  public void setMobile(String Mobile) {
    this.Mobile = Mobile;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String Email) {
    this.Email = Email;
  }

  public Patient getPatient() {
    return Patient;
  }

  public void setPatient(Patient Patient) {
    this.Patient = Patient;
  }

  public Long getPatientId() {
    return PatientId;
  }

  public void setPatientId(Long PatientId) {
    this.PatientId = PatientId;
  }

  public Long getPriority() {
    return Priority;
  }

  public void setPriority(Long Priority) {
    this.Priority = Priority;
  }
  
 // Join getters and setters
 public ContactType getType() {
    return Type;
  }

  public Long getTypeId() {
    return TypeId;
  }

  public void setTypeId(Long TypeId) {
    this.TypeId = TypeId;
  }
}