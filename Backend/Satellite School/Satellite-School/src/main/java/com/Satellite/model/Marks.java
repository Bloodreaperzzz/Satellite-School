package com.Satellite.model;


import com.Satellite.controller.TeacherController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="student_username", nullable = false)
    private Student student;

    private String subject;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="taught_by", nullable = false)
    private Teacher teacher;

    private String grade;
}
