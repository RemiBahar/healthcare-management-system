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
@Table(name="address_aud")
@IdClass(AddressAudId.class)
@Getter
@Setter
@NoArgsConstructor
public class AddressAud {

    @Id
    @Column(name="address_id")
    private Long AddressId;

    @Id
    @Column(name="rev")
    private Long Rev;

    @Column(name="revtype")
    private Long RevType;

    @Column(name="city")
    private String City;

    @Column(name="country")
    private String Country;

    @Column(name="description")
    private String Description;

    @Column(name="patient")
    private Long Patient;

    @Column(name="priority")
    private Long Priority;

    @Column(name="region")
    private String Region;

    @Column(name="street")
    private String Street;

    @Column(name="street_number")
    private String StreetNumber;

    @Column(name="type")
    private Long Type;

    @Column(name="zip_code")
    private String ZipCode;
   
}
