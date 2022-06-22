package com.cmd.hms.patient.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.validation.constraints.Size;

@Entity
@Table(name="country")
public class Country{
  // Fields
  @Id
  @Column(name="country_code", length = 2)
  @Size(min=2, max=2)
  private String CountryCode; // 3-letter country code used instead of auto-generated id

  @Column(name="name")
  private String Name;

  // Joins
  @OneToMany(mappedBy = "Type") // mappedBy refers to field name in child table pointing to this table
  private List<Address> Addresses;

  // Getters and Setters
  public void setCountryCode(String CountryCode){
    this.CountryCode = CountryCode;
  }

  public String getCountryCode() {
    return CountryCode;
  }

  public void setName(String Name){
    this.Name = Name;
  }

  public String getName() {
    return Name;
  }

}