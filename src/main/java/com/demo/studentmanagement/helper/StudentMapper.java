package com.demo.studentmanagement.helper;

import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentRequest;
import com.demo.studentmanagement.model.StudentServiceResponse;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

  public void mapStudentRequestToEntity(
      StudentRequest studentRequest, StudentEntity studentEntity) {
    studentEntity.setFirstName(studentRequest.getFirstName());
    studentEntity.setLastName(studentRequest.getLastName());
    studentEntity.setEmail(studentRequest.getEmailadress());
    studentEntity.setStandard(studentRequest.getStandard());
  }
}
