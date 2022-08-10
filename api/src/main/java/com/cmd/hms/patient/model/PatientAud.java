package com.cmd.hms.patient.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="patient_aud")
@IdClass(PatientAudId.class)
@Getter
@Setter
@NoArgsConstructor
public class PatientAud {

    @Id
    @Column(name="patient_id")
    private Long PatientId;

    @Id
    @Column(name="rev")
    private Long Rev;

    @Column(name="revtype")
    private Long RevType;

    @Column(name="date_of_birth")
    private Date DateOfBirth;

    @Column(name="first_name")
    private String FirstName;

    @Column(name="gender")
    private Long Gender;

    @Column(name="last_name")
    private String LastName;

    @Column(name="middle_name")
    private String MiddleName;

    @Column(name="patient_status")
    private Long PatientStatus;

    @Column(name="title")
    private Long Title;
   
}
