package com.demo.studentmanagement.web;

import com.demo.studentmanagement.model.StudentDTO;
import com.demo.studentmanagement.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** To handle student creation,updation,deletion and selction */
@RestController
@RequestMapping("/students")
public class StudentController {

  private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * To get the Student details by given student Id
   *
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public StudentDTO get(@PathVariable("id") Long id) {
    LOGGER.debug("Inside getStudent Method");
    return studentService.getById(id);
  }

  /**
   * To perform new student creation
   *
   * @param studentDTO
   * @return
   */
  @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
  public StudentDTO create(@Valid @RequestBody StudentDTO studentDTO) {
    return studentService.createStudent(studentDTO);
  }

  /**
   * To Perform updation on the existing student
   *
   * @param studentDTO
   * @return
   */
  @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
  public StudentDTO update(@RequestBody StudentDTO studentDTO) {
    return studentService.updateById(studentDTO);
  }

  /**
   * To perform student deletion by given Student Id
   *
   * @param id
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    studentService.deleteById(id);
  }
}
