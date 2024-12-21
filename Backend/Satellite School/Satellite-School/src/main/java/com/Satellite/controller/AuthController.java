package com.Satellite.controller;

import com.Satellite.config.JwtProvider;
import com.Satellite.model.*;
import com.Satellite.repository.GuardianRepository;
import com.Satellite.repository.Requested_StudentsRepository;
import com.Satellite.repository.StudentRepository;
import com.Satellite.repository.UserRepository;
import com.Satellite.requests.LoginRequests;
import com.Satellite.response.AuthResponse;
import com.Satellite.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//to return json rather that html to user from all methods
@RestController

//all methods and variabe written here have their endpoints starting with auth
@RequestMapping("/auth")
public class AuthController {
   
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Requested_StudentsRepository requested_studentsRepository;
     @PostMapping("/student/register")
    public ResponseEntity<String>createUserHandler(@RequestBody Requested_students student) throws Exception {
    Student doesAadharExist=studentRepository.findByAadhaarNumber(student.getAadhaarNumber());

    if(doesAadharExist!=null)
    {
        //return new ResponseEntity<>("Registeration Request submitted successfully.",HttpStatus.CREATED);
        throw new Exception("Student is already registered....");
    }
    Requested_students created_student=new Requested_students();
    created_student.setDob(student.getDob());
    created_student.setAddress(student.getAddress());
    created_student.setGender(student.getGender());
    created_student.setImage(student.getImage());
    created_student.setName(student.getName());
    created_student.setAadhaarNumber(student.getAadhaarNumber());
    created_student.setBloodGroup(student.getBloodGroup());
    created_student.setContactDetails(student.getContactDetails());


    requested_studentsRepository.save(created_student);








    return new ResponseEntity<>("Registeration Request submitted successfully.",HttpStatus.CREATED);
    }

@PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequests req)
    {
        String username=req.getUsername();
        String password=req.getPassword();
        Authentication authentication=authenticate(username ,password);

        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();

        String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
String jwt=jwtProvider.generateToken(authentication);
AuthResponse authResponse=new AuthResponse();

authResponse.setJwt(jwt);
authResponse.setMessage("Successfully logged in");
authResponse.setRole(role);
        System.out.println(authResponse);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);


    }

    private Authentication authenticate(String username, String password) {
    UserDetails userDetails =customUserDetailsService.loadUserByUsername(username);
    if(userDetails==null)
    {
        throw new BadCredentialsException("Invalid username..");

    }
    if(!passwordEncoder.matches(password,userDetails.getPassword()))
    {
        System.out.println("Stored password: " + userDetails.getPassword());
        System.out.println("Entered password: " + password);
        throw new BadCredentialsException("Invalid password...");
    }
    return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
