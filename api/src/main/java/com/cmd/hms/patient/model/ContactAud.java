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
@Table(name="contact_aud")
@IdClass(ContactAudId.class)
@Getter
@Setter
@NoArgsConstructor
public class ContactAud {

    @Id
    @Column(name="contact_id")
    private Long ContactId;

    @Id
    @Column(name="rev")
    private Long Rev;

    @Column(name="revtype")
    private Long RevType;

    @Column(name="email")
    private String Email;

    @Column(name="mobile")
    private String Mobile;

    @Column(name="name")
    private String Name;

    @Column(name="patient")
    private Long Patient;

    @Column(name="priority")
    private Long Priority;

    @Column(name="telephone")
    private String Telephone;

    @Column(name="type")
    private Long Type;
   
}
