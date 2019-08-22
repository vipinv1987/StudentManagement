package com.demo.studentmanagement.transformer;

import com.demo.studentmanagement.model.Student;
import com.demo.studentmanagement.model.StudentDTO;
import com.demo.studentmanagement.model.StudentRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StudentTransformer {

  public static Function<Student, StudentDTO> mapStudentToDto =
      new Function<Student, StudentDTO>() {
        public StudentDTO apply(Student t) {
          StudentDTO studentDTO = new StudentDTO();
          studentDTO.setStudentId(t.getId());
          studentDTO.setFirstName(t.getFirstName());
          studentDTO.setLastName(t.getLastName());
          studentDTO.setEmailAdress(t.getEmail());
          studentDTO.setStandard(t.getStandard());
          return studentDTO;
        }
      };

  public static Function<StudentDTO, Student> mapDtoToStudent =
          new Function<StudentDTO, Student>() {
            public Student apply(StudentDTO t) {
              Student student = new Student();
              student.setFirstName(t.getFirstName());
              student.setLastName(t.getLastName());
              student.setEmail(t.getEmailAdress());
              student.setStandard(t.getStandard());
              return student;
            }
          };
}
