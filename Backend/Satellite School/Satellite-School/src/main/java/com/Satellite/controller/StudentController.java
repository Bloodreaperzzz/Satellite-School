package com.Satellite.controller;

import com.Satellite.dto.GuardianDto;
import com.Satellite.model.Contact;
import com.Satellite.model.Guardian;
import com.Satellite.model.GuardianId;
import com.Satellite.model.Student;
import com.Satellite.repository.GuardianRepository;
import com.Satellite.repository.StudentRepository;
import com.Satellite.service.UserServiceImp;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private GuardianRepository guardianRepository;
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/addguardian")
    public ResponseEntity<String> addguardian(@RequestHeader("Authorization")String jwt, @RequestBody GuardianDto guardianDto) throws Exception {
        Student student=(Student)userServiceImp.findUserByJwtToken(jwt);
        System.out.println(student);
        Guardian store=new Guardian();
        store.setName(guardianDto.getName());
        Contact contact=new Contact();
        contact.setEmail(guardianDto.getEmail());
        contact.setPhone_number(guardianDto.getPhone());

        store.setContact(contact);
        store.setProfession(guardianDto.getProfession());
        store.setRelationship(guardianDto.getRelationship());
        GuardianId guardianId=new GuardianId();
        store.setStudent(student);
        guardianId.setGuardianAadhaar(guardianDto.getAadhaar());


        store.setGuardianId(guardianId);
        Guardian added=guardianRepository.save(store);
        Set<Guardian> guardians=student.getGuardians();
        guardians.add(added);
        student.setGuardians(guardians);
        studentRepository.save(student);
        return new ResponseEntity<>("guardian added successfully", HttpStatus.CREATED);




    }

}