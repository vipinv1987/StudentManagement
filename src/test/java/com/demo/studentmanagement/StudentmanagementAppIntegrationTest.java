package com.demo.studentmanagement;

import com.demo.studentmanagement.model.Standard;
import com.demo.studentmanagement.model.StudentDTO;
import com.demo.studentmanagement.repo.StudentRepo;
import com.demo.studentmanagement.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentmanagementAppIntegrationTest {

  @LocalServerPort private int port;

  @MockBean private StudentRepo studentRepo;

  private Optional<Student> student;

  private Student studentEntity;

  private TestRestTemplate restTemplate;

  private HttpHeaders headers;

  private HttpEntity<String> httpEntity;

  private ResponseEntity<String> response;

  private HttpEntity<StudentDTO> studentHttpEntity;

  @Before
  public void init() {
    restTemplate = new TestRestTemplate();
    headers = new HttpHeaders();
  }

  @Test
  public void testGet() throws Exception {
    GivenGetById();
    whenGetByID();
    thenCheckStatus();
  }

  private void whenGetByID() {
    response =
        restTemplate.exchange(
            createURLWithPort("/students/1"), HttpMethod.GET, httpEntity, String.class);
  }

  private void GivenGetById() {
    when(studentRepo.findById(1L)).thenReturn(createStudentEnity());
    httpEntity = new HttpEntity<String>(null, headers);
  }

  @Test
  public void testCreate() throws Exception {
    givenCreate();
    whenCreate();
    thenCheckStatus();
  }

  private void thenCheckStatus() {
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private void whenCreate() {
    response =
        restTemplate.exchange(
            createURLWithPort("/students/create"),
            HttpMethod.POST,
            studentHttpEntity,
            String.class);
  }

  private void givenCreate() {
    when(studentRepo.findById(1L)).thenReturn(createStudentEnity());
    when(studentRepo.save(any(Student.class))).thenReturn(updateStudentEntity());
    studentHttpEntity = new HttpEntity<StudentDTO>(createStudentRequest(), headers);
  }

  @Test
  public void testDeleteById() throws Exception {
    givenDeleteById();
    whenDeleteById();
    thenCheckStatus();
  }

  private void whenDeleteById() {
    response =
        restTemplate.exchange(
            createURLWithPort("/students/1"), HttpMethod.DELETE, httpEntity, String.class);
  }

  private void givenDeleteById() {
    when(studentRepo.findById(1L)).thenReturn(createStudentEnity());
    doNothing().when(studentRepo).delete(any(Student.class));
    httpEntity = new HttpEntity<String>(null, headers);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  private Optional<Student> createStudentEnity() {
    studentEntity = new Student();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    student = Optional.of(studentEntity);
    return student;
  }

  private Student updateStudentEntity() {
    studentEntity = new Student();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    return studentEntity;
  }

  private StudentDTO createStudentRequest() {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setFirstName("Vinu");
    studentDTO.setLastName("V");
    studentDTO.setEmailAdress("test@gmail.com");
    studentDTO.setStandard(Standard.ONE);
    return studentDTO;
  }
}
