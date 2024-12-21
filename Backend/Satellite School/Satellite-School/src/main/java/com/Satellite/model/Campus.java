package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String schoolid;

    private String name;
    @Embedded
   private Address address;
    private LocalDate establish_year;
    @Embedded
    private Contact contact_details;

    @Column(length=1000)
    private String image;

    @OneToOne
    @JoinColumn(name="Head Boy")
    private Student head_boy;
    @OneToOne
    @JoinColumn(name="Head Girl")
    private Student head_girl;

    @OneToMany(mappedBy="campus",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Teacher> teachers=new ArrayList<>();

    @OneToMany(mappedBy="campus",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Student> students=new ArrayList<>();

    @OneToOne
    @JoinColumn(name="Principal")
    private Teacher principal;






}
