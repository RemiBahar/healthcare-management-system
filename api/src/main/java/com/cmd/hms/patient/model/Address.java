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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.cmd.hms.patient.service.SecurityMethods;


 /**Used to represent an Address, stored in the address table and accessible via /Addresss. Linked to a Patient on a many-to-one basis.
*/
@Entity
@Table(name="address")
@Audited
public class Address {
   /**corresponds to auto-incremented, primary key address_id column
  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
  @Column(name="address_id")
  private Long AddressId;

   /**Corresponds to street_number, must be included in POST/PUT requests and has a maximum length of 1000 characters
  */
  @NotBlank(message = "Street number is mandatory")
  @Column(name="street_number", length = 1000) // String used since sometimes a house may have a name or letters
  @Size(max = 1000)
  private String StreetNumber;
  
  /**Corresponds to street, must be included in POST/PUT requests and has a maximum length of 1000 characters
  */
  @NotBlank(message = "Street is mandatory")
  @Column(name="street", length = 1000)
  @Size(max = 1000)
  private String Street;

  /**Corresponds to zip_code, has a maximum length of 10 characters
  */
  @Column(name="zip_code", length = 10)
  @Size(max = 10)
  private String ZipCode;

  /**Corresponds to city, must be included in POST/PUT requests and has a maximum length of 1000 characters
  */
  @NotBlank(message = "City is mandatory")
  @Column(name="city", length = 1000)
  @Size(max = 1000)
  private String City;

  /**Corresponds to street_number, has a maximum length of 10000 characters
  */
  @Column(name="description", length = 10000)
  @Size(max = 10000)
  private String Description;

  /**Corresponds to street_number, must be a positive integer
  */
  @Column(name="priority")
  @Positive
  private Long Priority;

  /**Corresponds to region, has a maximum length of 1000 characters
  */
  @Column(name="region", length = 1000)
  @Size(max = 1000)
  private String Region;


  // Joined fields

  /**Patient the address corresponds to, can be viewed with /Addresss(1)/PatientDetails and cannot be NULL
  */
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="patient",insertable = false, updatable = false)
  private Patient Patient;

   /** used to set Patient by passing {PatientId:1} in the HTTP request body, the value passed must be a correct foreign key to patient
  */
  @Column(name="patient")
  @NotNull(message = "Address must be linked to a Patient")
  private Long PatientId;

  /** Corresponds to type, can be viewed with /Addresss(1)/TypeDetails
   */
  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="type",insertable = false, updatable = false)
  @NotAudited
  private AddressType Type;

  /** Used for setting the Address Type by passing {TypeId:1} in the HTTP request body, the value passed must be a correct foreign key to address_type
   */
  @Column(name="type")
  private Long TypeId;

  /** Country of the address (country), can be viewed with /Addresss(1)/CountryDetails
   */
  @ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
  @JoinColumn(name="country", insertable = false, updatable = false)
  @NotAudited
  private Country Country;

  /** Used for setting Country by passing {CountryCode:"UK"} in the HTTP request body, value passed must be a correct foreign key to country
   */
  @NotBlank(message = "Country is mandatory")
  @Column(name="country", length = 2)
  @Size(min=2, max=2)
  private String CountryCode;

  //Constructor
  public Address(){}

  /** Used in GET requests to get the AddressId of an Address
   * 
   * @return  AddressId
   */
  public Long getAddressId() {
    if(new SecurityMethods().viewAddress(Patient)){
      return AddressId;
    } else{
        return 0L; // PK can't be NULL
    }
  }

  /** Used when adding a new Address with a POST request
   * 
   * @param AddressId cannot be set manually by the user
   */
  public void setAddressId(Long AddressId) {
    if(new SecurityMethods().editAddress()){
      this.AddressId = AddressId;
    } 
  }

  /** Used in GET requests to get the StreetNumber of an Address
   * 
   * @return StreetNumber - street number of the address, represented by a String since some addresses my have a name
   */
  public String getStreetNumber() {
    if(new SecurityMethods().viewAddress(Patient)){
      return StreetNumber;
    } else{
        return null;
    }
  }

  /** Used to set the StreetNumber in POST/PUT/PATCH requests
   * 
   * @param StreetNumber street number to set
   */
  public void setStreetNumber(String StreetNumber) {
    if(new SecurityMethods().editAddress()){
      this.StreetNumber = StreetNumber;
    } 
  }

  /** Used to get the Street in GET requests
   * 
   * @return Street
   */
  public String getStreet() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Street;
    } else{
        return null;
    }
  }

  /** Used to set the Street in POST/PUT/PATCH requests
   * 
   * @param Street street of the address
   */
  public void setStreet(String Street) {
    this.Street = Street;
  }

  /** Used to get the ZipCode in GET requests
   * 
   * @return ZipCode
   */
  public String getZipCode() {
    if(new SecurityMethods().viewAddress(Patient)){
      return ZipCode;
    } else{
        return null;
    }
  }

  /** Used to set the ZipCode in POST/PUT/PATCH requests
   * 
   * @param ZipCode depends on locality referred to as zip-code in the US, post-code in the UK, etc
   */
  public void setZipCode(String ZipCode) {
    if(new SecurityMethods().editAddress()){
      this.ZipCode = ZipCode;
    } 
  }

  /** Used to get the City in GET requests
   * 
   * @return this.City
   */
  public String getCity() {
    if(new SecurityMethods().viewAddress(Patient)){
      return City;
    } else{
        return null;
    }
  }

  /** Used to set the City in POST/PUT/PATCH requests
   *
   * @param City City/Town/Village/etc the Address is in
   */
  public void setCity(String City) {
    if(new SecurityMethods().editAddress()){
      this.City = City;
    } 
  }

  /** Used to get the priority in GET requests
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

  /** Used to set the Priority in POST/PUT/PATCH requests
   * 
   * @param Priority used to order the list of Addresses for a Patient
   */
  public void setPriority(Long Priority) {
    if(new SecurityMethods().editAddress()){
      this.Priority = Priority;
    } 
  }

  /** Used to get the Description in GET requests
   * 
   * @return Description
   */
  public String getDescription() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Description;
    } else{
        return null;
    }
  }

  /** Used to set the Description in POST/PUT/PATCH requests
   * 
   * @param Description any extra information about the Address
   */
  public void setDescription(String Description) {
    if(new SecurityMethods().editAddress()){
      this.Description = Description;
    } 
  }

  /** Used to get the Region in POST/PUT/PATCH requests
   * 
   * @return Region
   */
  public String getRegion() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Region;
    } else{
        return null;
    }
  }

  /** Used to set the Region in POST/PUT/PATCH requests
   * 
   * @param Region depends on locality, in the UK a region could refer to a county
   */
  public void setRegion(String Region) {
    if(new SecurityMethods().editAddress()){
      this.Region = Region;
    } 
  }

  // Getters and Setters for joined fields

  /** Used to get the Patient linked to the Address, can be shown inline with /Addresss(1)/PatientDetails
   * 
   * @return Patient
   */
  public Patient getPatient() { // Only have get object since set object is done using an Id
    if(new SecurityMethods().viewAddress(Patient)){
      return Patient;
    } else{
        return null;
    }
  }

  /** Used to get the PatientId of the Patient linked to the Address
   * 
   * @return PatientId
   */
  public Long getPatientId() {
    if(new SecurityMethods().viewAddress(Patient)){
      return PatientId;
    } else{
        return null;
    }
  }

  /** Used to link a Patient to an address by providing a PatientId
   * 
   * @param PatientId Integer identifying a Patient
   */
  public void setPatientId(Long PatientId) {
    if(new SecurityMethods().editAddress()){
      this.PatientId = PatientId;
    } 
  }

  /** Used to get the Type of the Address, can be shown inline with /Addresss(1)/TypeDetails
   * 
   * @return Type
   */
  public AddressType getType() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Type;
    } else{
        return null;
    }
  }

  /** Used to get the TypeId of the Type of the Address
   * 
   * @return TypeId
   */
  public Long getTypeId() {
    if(new SecurityMethods().viewAddress(Patient)){
      return TypeId;
    } else{
        return null;
    }
  }

  /** Used to set the Type of the Address using a TypeId
   * 
   * @param TypeId Integer identifying an AddressType
   */
  public void setTypeId(Long TypeId) {
    if(new SecurityMethods().editAddress()){
      this.TypeId = TypeId;
    } 
  }

  /** Used to get the Country the Address is in, can be shown inline with /Addresss(1)/CountryDetails 
   * 
   * @return Country
   */
  public Country getCountry() {
    if(new SecurityMethods().viewAddress(Patient)){
      return Country;
    } else{
        return null;
    }
  }

  /** Used to get the CountryCode of the Country the Address is in
   * 
   * @return CountryCode
   */
  public String getCountryCode() {
    if(new SecurityMethods().viewAddress(Patient)){
      return CountryCode;
    } else{
        return null;
    }
  }

  /** Used to set the Country the Address is in 
   * 
   * @param CountryCode 2-letter code identifying a Country e.g. UK
   */
  public void setCountryCode(String CountryCode) {
    if(new SecurityMethods().editAddress()){
      this.CountryCode = CountryCode;
    } 
  }

}