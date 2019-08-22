package com.demo.studentmanagement.web;

import com.demo.studentmanagement.model.StudentDTO;
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

  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/{id}")
  public StudentDTO get(@PathVariable("id") Long id) {
    LOGGER.debug("Inside getStudent Method");
    return studentService.getById(id);
  }

  @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
  public StudentDTO create(@RequestBody StudentDTO studentDTO) {
    return studentService.createStudent(studentDTO);
  }

  @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
  public StudentDTO update(@RequestBody StudentDTO studentDTO) {
    return studentService.updateById(studentDTO);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    studentService.deleteById(id);
  }
}
