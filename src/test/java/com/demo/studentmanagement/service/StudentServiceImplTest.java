package com.demo.studentmanagement.service;

import com.demo.studentmanagement.dao.StudentDAO;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

  @Mock
  StudentDAO studentDAO;

  @InjectMocks
  StudentServiceImpl studentService;

  @InjectMocks
  StudentMapper studentMapper;

  Optional<StudentEntity> student;

  StudentEntity studentEntity;

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
  public void createOrUpdateStudentTestForUpdate() {
    whenForCreateOrUpdateTest(createStudentEnity());
    studentService.createOrUpdateStudent(createUpdateStudentRequest());
  }

  public void whenForCreateOrUpdateTest(Optional<StudentEntity> studentEnity) {
    when(studentDAO.findById(1L)).thenReturn(studentEnity);
    when(studentDAO.save(studentEntity)).thenReturn(updateStudentEntity());
  }

  @Test
  public void createOrUpdateStudentTestForCreate() {
    whenForCreateOrUpdateTest(Optional.empty());
    studentService.createOrUpdateStudent(createUpdateStudentRequest());
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
