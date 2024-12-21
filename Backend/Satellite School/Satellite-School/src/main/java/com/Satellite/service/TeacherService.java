package com.Satellite.service;

import com.Satellite.model.*;

public interface TeacherService {
    public Student admitStudent(long aadhaar, String username , String password) throws Exception;

    void deleteRequestedTeacher(String aadhar);

    public void deleteRequested_student(long aadhaar);

    public Teacher admitTeacher(String aadhaar, String username , String password, long salary, Campus campus)throws Exception;
}
