package com.Satellite.controller;

import com.Satellite.requests.LoginRequests;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HomeController {

    @PostMapping("/new")
    public ResponseEntity<String> Homecontrol(@RequestBody LoginRequests logs){
        System.out.println("i am here mf");
        return new ResponseEntity<>("welcome", HttpStatus.CREATED);
    }
}
