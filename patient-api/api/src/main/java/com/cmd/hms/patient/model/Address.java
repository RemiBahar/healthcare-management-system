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

import javax.validation.constraints.Size;


@Entity
public class Address {
  // Standard fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
  @Column(name="address_id")
  private Long AddressId;

  @Column(name="street_number") // String used since sometimes a house may have a name or letters
  private String StreetNumber;

  @Column(name="street")
  private String Street;

  @Column(name="zip_code")
  private String ZipCode;

  @Column(name="city")
  private String City;

  @Column(name="description")
  private String Description;

  @Column(name="priority")
  private Long Priority;

  @Column(name="region")
  private String Region;


  // Joined fields

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="patient",insertable = false, updatable = false)
  private Patient Patient;

  @Column(name="patient")
  private Long PatientId;

  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="type",insertable = false, updatable = false)
  private AddressType Type;

  @Column(name="type")
  private Long TypeId;


  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="country", insertable = false, updatable = false)
  private Country Country;

  @Column(name="country", length = 2)
  @Size(min=2, max=2)
  private String CountryCode;

  //Constructor
  public Address(){}

  // Getters and setters
  public Long getAddressId() {
    return AddressId;
  }

  public void setAddressId(Long AddressId) {
    this.AddressId = AddressId;
  }

  public String getStreetNumber() {
    return StreetNumber;
  }

  public void setStreetNumber(String StreetNumber) {
    this.StreetNumber = StreetNumber;
  }

  public String getStreet() {
    return Street;
  }

  public void setStreet(String Street) {
    this.Street = Street;
  }

  public String getZipCode() {
    return ZipCode;
  }

  public void setZipCode(String ZipCode) {
    this.ZipCode = ZipCode;
  }

  public String getCity() {
    return City;
  }

  public void setCity(String City) {
    this.City = City;
  }

  public Long getPriority() {
    return Priority;
  }

  public void setPriority(Long Priority) {
    this.Priority = Priority;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String Description) {
    this.Description = Description;
  }

  public String getRegion() {
    return Region;
  }

  public void setRegion(String Region) {
    this.Region = Region;
  }

  // Getters and Setters for joined fields

  public Patient getPatient() { // Only have get object since set object is done using an Id
    return Patient;
  }

  public Long getPatientId() {
    return PatientId;
  }

  public void setPatientId(Long PatientId) {
    this.PatientId = PatientId;
  }

  public AddressType getType() {
    return Type;
  }

  public Long getTypeId() {
    return TypeId;
  }

  public void setTypeId(Long TypeId) {
    this.TypeId = TypeId;
  }

  public Country getCountry() {
    return Country;
  }

  public String getCountryCode() {
    return CountryCode;
  }

  public void setCountryCode(String CountryCode) {
    this.CountryCode = CountryCode;
  }

}