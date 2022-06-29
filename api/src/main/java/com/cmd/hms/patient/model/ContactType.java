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
import javax.validation.constraints.Size;

@Entity
@Table(name="contact_type")
public class ContactType{
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="contact_type_id")
  private Long ContactTypeId;

  @Column(name="title")
  @NotBlank(message="Title is mandatory")
  @Size(max = 100)
  private String Title;

  // Joins
  @OneToMany(mappedBy = "Type") // mappedBy refers to field name in child table pointing to this table
  private List<Contact> Contacts;

  // Getters and Setters
  public void setContactTypeId(Long ContactTypeId){
    this.ContactTypeId = ContactTypeId;
  }

  public Long getContactTypeId() {
    return ContactTypeId;
  }

  public void setTitle(String Title){
    this.Title = Title;
  }

  public String getTitle() {
    return Title;
  }

}