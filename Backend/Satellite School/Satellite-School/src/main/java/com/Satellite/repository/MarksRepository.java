package com.Satellite.repository;

import com.Satellite.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findByStudentUsername(String username);
}
