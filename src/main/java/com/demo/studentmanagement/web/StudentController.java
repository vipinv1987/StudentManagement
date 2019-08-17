package com.demo.studentmanagement.web;

import com.demo.studentmanagement.model.StudentRequest;
import com.demo.studentmanagement.model.StudentServiceResponse;
import com.demo.studentmanagement.model.StudentWebResponse;
import com.demo.studentmanagement.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

  @Autowired private StudentService studentService;

  @GetMapping("/{id}")
  public ResponseEntity<StudentWebResponse> getStudentDetails(@PathVariable("id") Long id) {
    LOGGER.debug("Inside getStudent Method");
    StudentServiceResponse studentServiceResponse = studentService.getStudentDetails(id);
    return new ResponseEntity<>(
        new StudentWebResponse(
            studentServiceResponse.getFirstName(),
            studentServiceResponse.getLastName(),
            studentServiceResponse.getEmailadress(),
            studentServiceResponse.getStandard()),
        HttpStatus.OK);
  }

  @PostMapping(
      path = "/createOrUpdate",
      consumes = "application/json",
      produces = "application/json")
  public HttpStatus createOrUpdateStudent(@RequestBody StudentRequest studentRequest) {
    studentService.createOrUpdateStudent(studentRequest);
    return HttpStatus.OK;
  }

  @DeleteMapping("/{id}")
  public HttpStatus deleteStudentById(@PathVariable("id") Long id) {
    studentService.deleteStudentById(id);
    return HttpStatus.OK;
  }
}
