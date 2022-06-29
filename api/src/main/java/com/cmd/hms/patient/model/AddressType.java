package com.cmd.hms.patient.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="address_type")
public class AddressType{
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="address_type_id")
  private Long AddressTypeId;

  @Column(name="title")
  @NotBlank(message = "Title is mandatory")
  private String Title;

  // Joins
  @OneToMany(mappedBy = "Type") // mappedBy refers to field name in child table pointing to this table
  private List<Address> Addresses;

  // Getters and Setters
  public void setAddressTypeId(Long AddressTypeId){
    this.AddressTypeId = AddressTypeId;
  }

  public Long getAddressTypeId() {
    return AddressTypeId;
  }

  public void setTitle(String Title){
    this.Title = Title;
  }

  public String getTitle() {
    return Title;
  }

}