package com.Satellite.service;

import com.Satellite.model.Marks;
import com.Satellite.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarksServiceIm implements MarksService{
    @Autowired
    private MarksRepository marksRepository;
    @Override
    public List<Marks> getMarksForStudent(String username) {
        return marksRepository.findByStudentUsername(username);
    }
}
