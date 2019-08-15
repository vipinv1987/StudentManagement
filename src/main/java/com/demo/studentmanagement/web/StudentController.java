package com.demo.studentmanagement.web;

import com.demo.studentmanagement.model.StudentServiceResponse;
import com.demo.studentmanagement.model.StudentWebResponse;
import com.demo.studentmanagement.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentWebResponse> getStudent(@PathVariable("id") Long id){
        LOGGER.debug("INSIDE getStudent Method");
        StudentServiceResponse studentServiceResponse = studentService.getStudentDetails(id);
        return new ResponseEntity<>(new StudentWebResponse(studentServiceResponse.getFirstName(),studentServiceResponse.getLastName(),studentServiceResponse.getEmailadress(),studentServiceResponse.getStandard()), HttpStatus.OK);
    }


}
