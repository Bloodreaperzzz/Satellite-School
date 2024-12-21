package com.Satellite.controller;

import com.Satellite.model.Houses;
import com.Satellite.model.Student;
import com.Satellite.model.User;
import com.Satellite.service.PassService;
import com.Satellite.service.PassServiceIm;
import com.Satellite.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasswordController {
    @Autowired
private UserServiceImp userServiceImp;
    @Autowired
    private PassServiceIm passServiceIm;
    @PostMapping ("/change-password")
    public ResponseEntity<String> findUserByJwtToken(@RequestHeader("Authorization")String jwt,@RequestParam String oldpassword,@RequestParam String newpassword) throws Exception
    {
        User user=  userServiceImp.findUserByJwtToken(jwt);
        try {
            passServiceIm.changePassword(user, oldpassword, newpassword);

            return new ResponseEntity<>("Changed", HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
