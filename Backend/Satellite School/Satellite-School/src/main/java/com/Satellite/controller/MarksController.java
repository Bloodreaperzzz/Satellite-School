    package com.Satellite.controller;

    import com.Satellite.model.Marks;
    import com.Satellite.model.Student;

    import com.Satellite.service.MarksServiceIm;
    import com.Satellite.service.UserServiceImp;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestHeader;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("/api")
    public class MarksController {



        @Autowired
        private MarksServiceIm marksService;
    @Autowired
    private UserServiceImp userServiceImp;
        @GetMapping("/student/marks")
        public ResponseEntity<Map<String, Object>> getStudentMarks(@RequestHeader("Authorization") String token) throws Exception {
            // Extract token after "Bearer "
            Student student= (Student) userServiceImp.findUserByJwtToken(token);
            // Fetch marks for the student
            List<Marks> marks = marksService.getMarksForStudent(student.getUsername());

            Map<String, Object> response = Map.of(
                    "name", student.getName(),
                    "marks", marks
            );

            return ResponseEntity.ok(response);
        }
    }
