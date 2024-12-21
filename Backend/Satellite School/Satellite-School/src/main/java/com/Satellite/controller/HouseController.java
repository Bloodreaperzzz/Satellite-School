package com.Satellite.controller;


import com.Satellite.model.Houses;
import com.Satellite.model.Student;
import com.Satellite.model.User;
import com.Satellite.service.StudentService;
import com.Satellite.service.StudentServiceIm;
import com.Satellite.service.UserService;
import com.Satellite.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class HouseController {
    private UserServiceImp userServiceImp;

    @GetMapping("/House")
    public ResponseEntity<Houses> findUserByJwtToken(@RequestHeader("Authorization")String jwt) throws Exception {
        Student student= (Student) userServiceImp.findUserByJwtToken(jwt);


        return new ResponseEntity<>(student.getHouse(), HttpStatus.CREATED);

    }


}
