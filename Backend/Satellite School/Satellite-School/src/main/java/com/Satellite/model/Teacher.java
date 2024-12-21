package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends User{
    private String name;
    private String qualification;
    private String gender;
    @Column(unique = true)
    private String Aadhaar;
    private LocalDate dob;
    @Embedded
    private Address address;
    private String blood_group;
    @Column(length=1000)
    private String image;
@JsonManagedReference
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;

private long salary;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<Marks> marks = new HashSet<>();






}
