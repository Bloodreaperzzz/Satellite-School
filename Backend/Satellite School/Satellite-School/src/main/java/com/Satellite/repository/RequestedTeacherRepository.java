package com.Satellite.repository;

import com.Satellite.model.Requested_teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestedTeacherRepository  extends JpaRepository<Requested_teacher,String> {
    public Requested_teacher findByAadhaarNumber(String aadhaar);


}
