package com.cmd.hms.patient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="gender")
public class Gender {
    /** Represents a gender. Decoupled from Patient by being unidirectional, allowing it to be reused for different classes.
     * @param GenderId Primary key of gender
     * @param Title Type of gender e.g. Female
     * @param IsDeleted Flag to determine whether gender is in-use or not
    */

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
    @Column(name="gender_id")
    private Long GenderId;

    @NotBlank
    @Column(name="title")
    private String Title;

    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean IsDeleted = false;

    // Getters and setters
    public Long getGenderId() {
        return GenderId;
    }

    public void setGenderId(Long GenderId) {
        this.GenderId = GenderId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public Boolean getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
}