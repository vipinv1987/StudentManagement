package com.demo.studentmanagement.helper;

import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentServiceResponse;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static StudentServiceResponse mapStudentDetails(StudentEntity studentEntity){
        return new StudentServiceResponse(studentEntity.getFirstName(), studentEntity.getLastName(), studentEntity.getEmail(), studentEntity.getStandard());
    }

}
