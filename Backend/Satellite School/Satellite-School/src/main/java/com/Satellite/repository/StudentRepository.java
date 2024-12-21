package com.Satellite.repository;

import com.Satellite.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    public Student findByAadhaarNumber(long aadhaarNumber);
    public Student findByUsername(String username);
}
