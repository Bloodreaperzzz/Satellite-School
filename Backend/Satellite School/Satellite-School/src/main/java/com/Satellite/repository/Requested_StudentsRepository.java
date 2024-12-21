package com.Satellite.repository;

import com.Satellite.model.Requested_students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Requested_StudentsRepository extends JpaRepository<Requested_students,Long> {
public Requested_students findByAadhaarNumber(long aadhaarNumber);
}
