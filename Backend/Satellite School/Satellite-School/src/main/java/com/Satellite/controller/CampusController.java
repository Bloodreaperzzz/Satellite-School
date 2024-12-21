package com.Satellite.controller;

import com.Satellite.model.Campus;

import com.Satellite.service.CampusServiceIm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campuses")
public class CampusController {

    @Autowired
    private CampusServiceIm campusService;

    @GetMapping
    public ResponseEntity<List<Campus>> getAllCampuses() {
       return new ResponseEntity<>(campusService.getCampus(), HttpStatus.OK);
//        System.out.println("called");
//        return new ResponseEntity<>(campusService.getCampus(),HttpStatus.CREATED);
    }
}