package com.cmd.hms.patient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="title")
public class Title {
    /** Represents a title. Decoupled from Patient by being unidirectional, allowing it to be reused for different classes.
     * @param TitleId Primary key of title
     * @param Title Type of title e.g. Mr
    */

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Id sequencing unique for this table
    @Column(name="title_id")
    private Long TitleId;

    @NotBlank
    @Column(name="title")
    private String Title;

    // Getters and setters
    public Long getTitleId() {
        return TitleId;
    }

    public void setTitleId(Long TitleId) {
        this.TitleId = TitleId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
}