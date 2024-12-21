package com.Satellite.service;

import com.Satellite.model.Guardian;
import com.Satellite.model.Student;
import com.Satellite.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceIm implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void addGuardian(Guardian guardian,String username) throws Exception {
        Student student =studentRepository.findByUsername(username);
        if(student ==null)
        {
            throw new Exception("Student not found");
        }



    }
}
