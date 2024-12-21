package com.Satellite.service;

import com.Satellite.model.*;
import com.Satellite.repository.RequestedTeacherRepository;
import com.Satellite.repository.Requested_StudentsRepository;
import com.Satellite.repository.StudentRepository;
import com.Satellite.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceIm  implements TeacherService{
    @Autowired
  private Requested_StudentsRepository requested_studentsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RequestedTeacherRepository requestedTeacherRepository;
@Autowired
private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student admitStudent(long aadhaar, String username , String password) throws Exception {
        Student stu =new Student();
        Requested_students req=requested_studentsRepository.findByAadhaarNumber(aadhaar);
        if(req==null)
        {
            throw new Exception("student not found");
        }
        stu.setName(req.getName());
        stu.setAddress(req.getAddress());
        stu.setImage(req.getImage());
        stu.setGender(req.getGender());
        stu.setDob(req.getDob());
        stu.setBlood_Group(req.getBloodGroup());
        stu.setAadhaarNumber(req.getAadhaarNumber());


        stu.setUsername(username);
        stu.setContact_details(req.getContactDetails());
        stu.setPassword(passwordEncoder.encode(password));
        deleteRequested_student(aadhaar);
        System.out.println("fbdjh");
       return studentRepository.save(stu);



    }
    @Override
    public Teacher admitTeacher(String aadhaar, String username , String password, long salary, Campus campus) throws Exception {
      Teacher teacher=new Teacher();
        Requested_teacher req=requestedTeacherRepository.findByAadhaarNumber(aadhaar);
        if(req==null)
        {
            throw new Exception("teacher not found");
        }
       teacher.setName(req.getName());
        teacher.setDob(req.getDob());
        teacher.setGender(req.getGender());
        teacher.setAddress(req.getAddress());
        teacher.setBlood_group(req.getBlood_Group());
        teacher.setAadhaar(req.getAadhaarNumber());
        teacher.setQualification(req.getQualification());
        teacher.setImage(req.getImage());
        teacher.setContact_details(req.getContact());
    teacher.setCampus(campus);

        teacher.setPassword(passwordEncoder.encode(password));
        teacher.setUsername(username);
        teacher.setSalary(salary);

        deleteRequestedTeacher(aadhaar);

        return teacherRepository.save(teacher);



    }
    @Override
    public void deleteRequestedTeacher(String aadhar)
    {
        requestedTeacherRepository.deleteById(aadhar);
    }


    @Override
    public void deleteRequested_student(long aadhar) {
      requested_studentsRepository.deleteById(aadhar);

    }

}
