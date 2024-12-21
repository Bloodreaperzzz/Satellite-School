package com.Satellite.controller;

import com.Satellite.dto.LocationDto;
import com.Satellite.model.*;
import com.Satellite.repository.TransportWorkerRepository;
import com.Satellite.service.EmailService;
import com.Satellite.service.UserServiceImp;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transport_worker")
public class TransportController {
    @Autowired
    private TransportWorkerRepository transportWorkerRepository;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private EmailService emailService;

    @PostMapping("send_mail")
    public ResponseEntity<String> sendlocation(@RequestHeader("Authorization")String jwt, @RequestBody LocationDto location) throws Exception {
        User user=userServiceImp.findUserByJwtToken(jwt);
        TransportWorker transportWorker=(TransportWorker) user;

        String subject="Live Location";
        String body=location.getLocation()+"\nBus Number : "+transportWorker.getAssignedVehicle().getVehicleId()+"\nDriver Details :- \nName : "+transportWorker.getName()+"\nPhone Number : "+transportWorker.getContact_details().getPhone_number();
        System.out.println(transportWorker);
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

    @PostMapping("send_SOS")
    public ResponseEntity<String> sendSOS(@RequestHeader("Authorization")String jwt, @RequestBody LocationDto location) throws Exception {
        User user=userServiceImp.findUserByJwtToken(jwt);
        TransportWorker transportWorker=(TransportWorker) user;

        String subject="EMERGENCY";
        String body="Location : "+location.getLocation()+"\nBus Number : "+transportWorker.getAssignedVehicle().getVehicleId()+"\nDriver Details :- \nName : "+transportWorker.getName()+"\nPhone Number : "+transportWorker.getContact_details().getPhone_number()+"\nReason : "+location.getReason();
        System.out.println(transportWorker);
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
}