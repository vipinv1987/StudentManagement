package com.demo.studentmanagement.service;

import com.demo.studentmanagement.dao.StudentDAO;
import com.demo.studentmanagement.helper.StudentMapper;
import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentRequest;
import com.demo.studentmanagement.model.StudentServiceResponse;
import com.demo.studentmanagement.model.StudentWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentServiceResponse getStudentDetails(Long studentId) {
        Optional<StudentEntity> student = studentDAO.findById(studentId);
        StudentServiceResponse studentServiceResponse =null;
        if(student.isPresent()){
            studentServiceResponse = studentMapper.mapStudentDetails(student.get());
        }

        return studentServiceResponse;
    }

    @Override
    public void createOrUpdateStudent(StudentRequest studentRequest) {
        Optional<StudentEntity> student = studentDAO.findById(studentRequest.getStudentId());
        if(student.isPresent()){
            StudentEntity studentEntity = student.get();
            studentSaveOrUpdate(studentRequest, studentEntity);
        }
        else{
            StudentEntity studentEntity = new StudentEntity();
            studentSaveOrUpdate(studentRequest, studentEntity);
        }
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Optional<StudentEntity> student = studentDAO.findById(studentId);

        if(student.isPresent())
        {
            studentDAO.deleteById(studentId);
        }
    }

    private void studentSaveOrUpdate(StudentRequest studentRequest, StudentEntity studentEntity) {
        studentMapper.mapStudentRequestToEntity(studentRequest,studentEntity);
        studentDAO.save(studentEntity);
    }


}
