package com.Satellite.controller;

import com.Satellite.dto.StudentDto;
import com.Satellite.model.*;
import com.Satellite.repository.CampusRepository;
import com.Satellite.repository.HouseRepository;
import com.Satellite.repository.Requested_StudentsRepository;
import com.Satellite.requests.LoginRequests;
import com.Satellite.service.EmailService;
import com.Satellite.service.TeacherServiceIm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/founder_manager")
public class AdmitController {
@Autowired
private CampusRepository campusRepository;

    @Autowired
    private TeacherServiceIm teacherServiceIm;
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Requested_StudentsRepository requested_studentsRepository;
    @PostMapping("/admit-student/{id}")
    public ResponseEntity<Student>admit_student(@PathVariable long id, @RequestBody LoginRequests loginRequests) throws Exception {

        Student saved=teacherServiceIm.admitStudent(id,loginRequests.getUsername(),loginRequests.getPassword());
       String body="your username is "+saved.getUsername()+"you password is "+loginRequests.getPassword();
        //String body="u have received a course drop";
        String subject="Admitted!!!!!";

            //emailService.sendEmail(saved.getContact_details().getEmail(), subject, body);

    emailService.sendEmail(saved.getContact_details().getEmail(), subject, body);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/requested-students")
    public ResponseEntity<List<StudentDto>> getRequestedStudents() {
        List<Requested_students> requestedStudents = requested_studentsRepository.findAll();
        List<StudentDto> studentDTOs = requestedStudents.stream()
                .map(student -> new StudentDto(student.getName(), student.getAadhaarNumber()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }



    @PostMapping("/admit-teacher/{id}")
    public ResponseEntity<Teacher>admit_teacher(@PathVariable String id, @RequestBody LoginRequests loginRequests, @RequestParam String Campusid, @RequestParam long salary) throws Exception {
       Campus campus=campusRepository.findBySchoolid(Campusid);


        Teacher saved=teacherServiceIm.admitTeacher(id,loginRequests.getUsername(),loginRequests.getPassword(),salary,campus);
        String body="your username is "+saved.getUsername()+"you password is "+saved.getPassword();
        //String body="u have received a course drop";
        String subject="Admitted!!!!!";

        //emailService.sendEmail(saved.getContact_details().getEmail(), subject, body);

        emailService.sendEmail(saved.getContact_details().getEmail(), subject, body);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }





}
