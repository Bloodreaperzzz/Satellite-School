package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)  // Optional: you can control which fields to include
@ToString(exclude = "student")
public class Guardian {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private GuardianId guardianId;


    @ManyToOne
    @JsonBackReference
    @MapsId("studentUsername")
    @JoinColumn(name="student_username", nullable = false)
    private Student student;

    private String name;
    private String profession;

    private String relationship;
    @Embedded
    private Contact contact;


}