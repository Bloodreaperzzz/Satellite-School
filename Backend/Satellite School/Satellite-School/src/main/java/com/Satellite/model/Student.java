package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data//getter setter methods
@NoArgsConstructor
@AllArgsConstructor

public class Student extends User {

    private String name;
    @Column(unique = true)
    private long aadhaarNumber;
    private LocalDate dob;
    private String Blood_Group;
    private String gender;

    @Column(length=1000)
    private String image;
@Embedded
private Address address;


    @ManyToOne
    @JoinColumn(name="house")
    @JsonManagedReference
    private Houses house;

    @OneToMany(mappedBy = "student")
 @JsonIgnore
    private Set<Guardian> guardians = new HashSet<>();


    @ManyToOne
    @JoinColumn(name="vehicle_id")
    @JsonIgnore
    private TransportVehicle vehicle;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;

    private String studyClass;


    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Marks> marks = new HashSet<>();














}
