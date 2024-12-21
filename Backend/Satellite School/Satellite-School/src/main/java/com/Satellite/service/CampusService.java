package com.Satellite.service;

import com.Satellite.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampusService  {
    public List<Campus> getCampus();
}
