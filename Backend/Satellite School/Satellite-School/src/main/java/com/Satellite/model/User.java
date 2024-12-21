package com.Satellite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "user", indexes = {
        @Index(name = "idx_username", columnList = "username")
})
@Inheritance(strategy = InheritanceType.JOINED)
public  class User {

    @Id
    private String username;
    @JsonIgnore
private String password;
@Embedded
private Contact contact_details;




}
