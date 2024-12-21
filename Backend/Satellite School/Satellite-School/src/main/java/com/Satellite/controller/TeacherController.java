package com.Satellite.controller;

import com.Satellite.dto.GradeDto;
import com.Satellite.model.Marks;
import com.Satellite.model.Student;
import com.Satellite.model.Teacher;
import com.Satellite.repository.MarksRepository;
import com.Satellite.repository.StudentRepository;
import com.Satellite.repository.TeacherRepository;
import com.Satellite.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Endpoint to assign a grade to a student for a subject
    @PostMapping("/assignGrade")
    public ResponseEntity<String> assignGrade(@RequestHeader("Authorization") String jwt,
                                              @RequestBody GradeDto gradeDto) {
        try {
            // Find the teacher (assuming JWT token contains teacher info)
            Teacher teacher = (Teacher)userServiceImp.findUserByJwtToken(jwt);

            // Find the student
            Student student = studentRepository.findById(gradeDto.getStudentId()).orElse(null);
            if (student == null) {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }

            // Create a new Marks object to store the grade
            Marks marks = new Marks();
            marks.setStudent(student);
            marks.setTeacher(teacher);
            marks.setSubject(gradeDto.getSubject());
            marks.setGrade(gradeDto.getGrade());

            // Save the marks entry
            marksRepository.save(marks);

            return new ResponseEntity<>("Grade assigned successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while assigning grade: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
