package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Houses {
    @Id
    private String name;
    private String trait;
    private String color;

    @OneToMany(mappedBy = "house")
    @JsonBackReference
    private List<Student> students=new ArrayList<>();
    @OneToOne
    @JoinColumn(name="prefect")
    private Student prefect;
    @OneToOne
    @JoinColumn(name="teacher-incharge")
    private Teacher teacher;
}
