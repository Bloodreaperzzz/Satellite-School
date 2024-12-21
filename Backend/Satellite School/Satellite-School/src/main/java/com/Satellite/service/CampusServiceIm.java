package com.Satellite.service;

import com.Satellite.model.Campus;
import com.Satellite.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CampusServiceIm implements CampusService{

    @Autowired
    private CampusRepository campusRepository;
     @Override
    public List<Campus> getCampus()
    {
return campusRepository.findAll();
    }


}
