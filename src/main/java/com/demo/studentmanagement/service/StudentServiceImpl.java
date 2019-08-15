package com.demo.studentmanagement.service;

import com.demo.studentmanagement.dao.StudentDAO;
import com.demo.studentmanagement.helper.StudentMapper;
import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentServiceResponse;
import com.demo.studentmanagement.model.StudentWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public StudentServiceResponse getStudentDetails(Long id) {
        Optional<StudentEntity> student = studentDAO.findById(id);
        StudentServiceResponse studentServiceResponse =null;
        if(student.isPresent()){
            studentServiceResponse = studentMapper.mapStudentDetails(student.get());
        }

        return studentServiceResponse;
    }
}
