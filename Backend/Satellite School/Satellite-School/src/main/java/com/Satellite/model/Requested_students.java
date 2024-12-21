package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        indexes = @Index(name = "idx_aadhaar_number", columnList = "aadhaarNumber") // Adding index here
)
public class Requested_students {
    private String name;
    @Id
    private long aadhaarNumber;
    private LocalDate dob;
    private String bloodGroup;
    private String gender;

    @Column(length=1000)
    private String image;
    @Embedded
    private Address address;


@Embedded
private Contact contactDetails;

}
