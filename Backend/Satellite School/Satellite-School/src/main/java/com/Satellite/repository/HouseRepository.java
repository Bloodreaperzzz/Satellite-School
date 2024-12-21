package com.Satellite.repository;

import com.Satellite.model.Houses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<Houses,String> {
    public Houses findByName(String name);
}
