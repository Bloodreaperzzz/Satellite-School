package com.Satellite.repository;

import com.Satellite.model.Campus;
import com.Satellite.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus,String> {
    public boolean existsByPrincipal(Teacher principal);
    public Campus findBySchoolid(String school);
}
