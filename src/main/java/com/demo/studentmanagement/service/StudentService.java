package com.demo.studentmanagement.service;

import com.demo.studentmanagement.model.StudentDTO;
import com.demo.studentmanagement.model.StudentRequest;

public interface StudentService {

    public StudentDTO getById(Long studentId);
    public StudentDTO createStudent(StudentDTO studentDTO);
    public StudentDTO updateById(StudentDTO studentDTO);
    public void deleteById(Long studentId);

}
