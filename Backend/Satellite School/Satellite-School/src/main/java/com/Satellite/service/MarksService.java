package com.Satellite.service;

import com.Satellite.model.Marks;

import java.util.List;

public interface MarksService {

    public List<Marks> getMarksForStudent(String username);
}
