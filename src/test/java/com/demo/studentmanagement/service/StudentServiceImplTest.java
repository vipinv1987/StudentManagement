package com.demo.studentmanagement.service;

import com.demo.studentmanagement.repo.StudentRepo;
import com.demo.studentmanagement.helper.StudentMapper;
import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

  @Mock
  private StudentRepo studentDAO;

  @InjectMocks
  private StudentServiceImpl studentService;

  @InjectMocks
  private StudentMapper studentMapper;

  private Optional<StudentEntity> student;

  private StudentEntity studentEntity;

  @Before
  public void init() {
    ReflectionTestUtils.setField(studentService, "studentMapper", studentMapper);
  }

  @Test
  public void getStudentDetailsTest() {
    when(studentDAO.findById(1L)).thenReturn(createStudentEnity());
    studentService.getStudentDetails(1L);
  }

  @Test
  public void createStudentTest() {
    when(studentDAO.save(any(StudentEntity.class))).thenReturn(updateStudentEntity());
    studentService.createStudent(createUpdateStudentRequest());
  }

  public void whenForCreateOrUpdateTest(Optional<StudentEntity> studentEnity) {
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
    doNothing().when(studentDAO).delete(any(StudentEntity.class));
    studentService.deleteStudentById(1L);
  }

  private Optional<StudentEntity> createStudentEnity() {
    studentEntity = new StudentEntity();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    student = Optional.of(studentEntity);
    return student;
  }

  private StudentRequest createUpdateStudentRequest() {
    return new StudentRequest(1L, "Vinu", "V", "vinu@gmail.com", 6);
  }

  private StudentEntity updateStudentEntity() {
    studentEntity = new StudentEntity();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    return studentEntity;
  }
}
