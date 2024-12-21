package com.Satellite.dto;


import lombok.Data;

@Data
public class GuardianDto {

    private long Aadhaar;


    private String name;
    private String profession;

    private String relationship;

    private String email;
    private long phone;
}
