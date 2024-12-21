package com.Satellite.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Requested_teacher {

    private String name;
    @Id
    private String aadhaarNumber;

    private String qualification;
    private String gender;
    private LocalDate dob;
    @Embedded
    private Address address;
    private String blood_Group;
    @Column(length=1000)
    private String image;
private Contact contact;



}
