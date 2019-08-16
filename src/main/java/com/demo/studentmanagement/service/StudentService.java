package com.demo.studentmanagement.service;

import com.demo.studentmanagement.model.StudentRequest;
import com.demo.studentmanagement.model.StudentServiceResponse;

public interface StudentService {

    public StudentServiceResponse getStudentDetails(Long studentId);
    public void createOrUpdateStudent(StudentRequest studentRequest);
    public void deleteStudentById(Long studentId);

}
