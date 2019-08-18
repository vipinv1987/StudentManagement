package com.demo.studentmanagement.service;

import com.demo.studentmanagement.exception.StudentNotFoundException;
import com.demo.studentmanagement.helper.StudentMapper;
import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentRequest;
import com.demo.studentmanagement.model.StudentServiceResponse;
import com.demo.studentmanagement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired private StudentRepo studentDAO;

  @Autowired private StudentMapper studentMapper;

  @Override
  public StudentServiceResponse getStudentDetails(Long studentId) {
    return studentDAO
        .findById(studentId)
        .map(
            studentEntity -> {
              return new StudentServiceResponse(
                  studentEntity.getFirstName(),
                  studentEntity.getLastName(),
                  studentEntity.getEmail(),
                  studentEntity.getStandard());
            })
        .orElseThrow(() -> new StudentNotFoundException("StudentId " + studentId + " Not Found"));
  }

  @Override
  public void createOrUpdateStudent(StudentRequest studentRequest) {
    Optional<StudentEntity> student = studentDAO.findById(studentRequest.getStudentId());
    if (student.isPresent()) {
      StudentEntity studentEntity = student.get();
      studentSaveOrUpdate(studentRequest, studentEntity);
    } else {
      StudentEntity studentEntity = new StudentEntity();
      studentSaveOrUpdate(studentRequest, studentEntity);
    }
  }

  @Override
  public void deleteStudentById(Long studentId) {
     studentDAO.findById(studentId).map(studentEntity -> {
       studentDAO.delete(studentEntity);
       return studentEntity;
    }).orElseThrow(()->new StudentNotFoundException("StudentId " + studentId + " Not Found"));
  }

  private void studentSaveOrUpdate(StudentRequest studentRequest, StudentEntity studentEntity) {
    studentMapper.mapStudentRequestToEntity(studentRequest, studentEntity);
    studentDAO.save(studentEntity);
  }
}
