package com.Satellite.controller;

import com.Satellite.model.Guardian;
import com.Satellite.model.Student;
import com.Satellite.model.TransportWorker;
import com.Satellite.model.User;
import com.Satellite.service.EmailService;
import com.Satellite.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private EmailService emailService;
@Autowired
private UserServiceImp userServiceImp;
    private String sharedLink = ""; // Link to the live location map

    @PostMapping("/share-location")
    public ResponseEntity<String> shareLocation(@RequestBody Map<String, Double> location,@RequestHeader("Authorization") String token) throws Exception {
        double latitude = location.get("latitude");
        double longitude = location.get("longitude");
        sharedLink = "https://www.google.com/maps?q=" + latitude + "," + longitude;
        User user=userServiceImp.findUserByJwtToken(token);
        TransportWorker transportWorker=(TransportWorker) user;
        System.out.println(transportWorker);

        String subject="Live Location";
        String body=sharedLink+"\nBus Number : "+transportWorker.getAssignedVehicle().getVehicleId()+"\nDriver Details :- \nName : "+transportWorker.getName()+"\nPhone Number : "+transportWorker.getContact_details().getPhone_number();

        for(Student student:transportWorker.getAssignedVehicle().getStudents())
        {
            System.out.println(student);
            System.out.println(student.getGuardians());
            for(Guardian guardian:student.getGuardians())
            {
                emailService.sendEmail(guardian.getContact().getEmail(), subject, body);
                System.out.print(guardian);
            }
        }

        return new ResponseEntity<>("successfully sent mail to all guardians", HttpStatus.CREATED);
    }

    @GetMapping("/get-location-link")
    public ResponseEntity<String> getLocationLink() {
        return ResponseEntity.ok(sharedLink);
    }
}
