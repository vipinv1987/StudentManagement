package com.demo.studentmanagement.service;

import com.demo.studentmanagement.model.Standard;
import com.demo.studentmanagement.model.Student;
import com.demo.studentmanagement.model.StudentDTO;
import com.demo.studentmanagement.repo.StudentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

  @Mock private StudentRepo studentRepo;

  @InjectMocks private StudentServiceImpl studentService;
  private Optional<Student> student;

  private Student studentEntity;

  @Test
  public void getStudentDetailsTest() {
    givenStudentService();
    whenGetById();
  }

  private void givenStudentService() {
    studentService = new StudentServiceImpl(studentRepo);
  }

  private void whenGetById() {
    when(studentRepo.findById(1L)).thenReturn(createStudent());
    studentService.getById(1L);
  }

  @Test
  public void createStudentTest() {
    givenStudentService();
    whenCreate();
  }

  private void whenCreate() {
    when(studentRepo.save(any(Student.class))).thenReturn(updateStudentEntity());
    studentService.createStudent(createRequest());
  }

  @Test
  public void updateStudentTest() {
    givenStudentService();
    whenUpdateById();
  }

  private void whenUpdateById() {
    when(studentRepo.findById(any(Long.class))).thenReturn(createStudent());
    when(studentRepo.save(studentEntity)).thenReturn(updateStudentEntity());
    studentService.updateById(updateRequest());
  }

  @Test
  public void deleteByIdTest() {
    whenDeleteById();
    studentService.deleteById(1L);
  }

  private void whenDeleteById() {
    when(studentRepo.findById(anyLong())).thenReturn(createStudent());
    doNothing().when(studentRepo).delete(any(Student.class));
  }
  /*
  public void whenForCreateOrUpdateTest(Optional<Student> studentEnity) {
    when(studentDAO.findById(1L)).thenReturn(studentEnity);
    when(studentDAO.save(studentEntity)).thenReturn(updateStudentEntity());
  }

  @Test
  public void updateStudentTest() {
    when(studentDAO.findById(any(Long.class))).thenReturn(createStudentEnity());
    when(studentDAO.save(studentEntity)).thenReturn(updateStudentEntity());
    studentService.updateStudent(createUpdateStudentRequest());
  }

  @Test
  public void deleteStudentByIdTest() {
    when(studentDAO.findById(1L)).thenReturn(createStudentEnity());
    doNothing().when(studentDAO).delete(any(Student.class));
    studentService.deleteStudentById(1L);
  }*/

  private Optional<Student> createStudent() {
    studentEntity = new Student();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    studentEntity.setStandard(Standard.ONE);
    student = Optional.of(studentEntity);
    return student;
  }

  private StudentDTO createRequest() {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setFirstName("Vinu");
    studentDTO.setLastName("V");
    studentDTO.setEmailAdress("test@gmail.com");
    studentDTO.setStandard(Standard.ONE);
    return studentDTO;
  }

  private StudentDTO updateRequest() {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setStudentId(1L);
    studentDTO.setFirstName("Vinu");
    studentDTO.setLastName("V");
    studentDTO.setEmailAdress("test@gmail.com");
    studentDTO.setStandard(Standard.ONE);
    return studentDTO;
  }

  private Student updateStudentEntity() {
    studentEntity = new Student();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    return studentEntity;
  }
}
