package com.demo.studentmanagement.service;

import com.demo.studentmanagement.exception.DataNotFoundException;
import com.demo.studentmanagement.transformer.StudentTransformer;
import com.demo.studentmanagement.model.Student;
import com.demo.studentmanagement.model.StudentDTO;
import com.demo.studentmanagement.model.StudentRequest;
import com.demo.studentmanagement.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  private StudentRepo studentRepo;

  public StudentServiceImpl(StudentRepo studentRepo) {
    this.studentRepo = studentRepo;
  }

  @Override
  public StudentDTO getById(Long studentId) {

    return studentRepo
        .findById(studentId)
        .map(student -> StudentTransformer.mapStudentToDto.apply(student))
        .orElseThrow(DataNotFoundException::new);
  }

  @Override
  public StudentDTO createStudent(StudentDTO studentDTO) {
    Student student = StudentTransformer.mapDtoToStudent.apply(studentDTO);
    studentRepo.save(student);
    return StudentTransformer.mapStudentToDto.apply(student);
  }

  @Override
  public StudentDTO updateById(StudentDTO studentDTO) {
    return studentRepo
        .findById(studentDTO.getStudentId())
        .map(
            student -> {
              StudentTransformer.mapDtoToStudent.apply(studentDTO);
              studentRepo.save(student);
              return StudentTransformer.mapStudentToDto.apply(student);
            })
        .orElseThrow(
            () ->
                new DataNotFoundException("StudentId " + studentDTO.getStudentId() + " Not Found"));
  }

  @Override
  public void deleteById(Long studentId) {
    studentRepo.findById(studentId).ifPresent(studentRepo::delete);
  }
}
