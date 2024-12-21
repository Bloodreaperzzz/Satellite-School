package com.Satellite.repository;

import com.Satellite.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
}
