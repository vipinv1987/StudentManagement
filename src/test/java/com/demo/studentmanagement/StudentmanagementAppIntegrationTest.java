package com.demo.studentmanagement;

import com.demo.studentmanagement.repo.StudentRepo;
import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentRequest;
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

  @MockBean private StudentRepo studentDAO;

  private Optional<StudentEntity> student;

  private StudentEntity studentEntity;

  private TestRestTemplate restTemplate;

  private HttpHeaders headers;

  @Before
  public void init() {
    restTemplate = new TestRestTemplate();
    headers = new HttpHeaders();
  }

  @Test
  public void testStudentDetails() throws Exception {
    when(studentDAO.findById(1L)).thenReturn(createStudentEnity());
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/students/1"), HttpMethod.GET, entity, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testCreateStudent() throws Exception {
    when(studentDAO.findById(1L)).thenReturn(createStudentEnity());
    when(studentDAO.save(any(StudentEntity.class))).thenReturn(updateStudentEntity());
    HttpEntity<StudentRequest> entity =
        new HttpEntity<StudentRequest>(createStudentRequest(), headers);
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/students/create"), HttpMethod.POST, entity, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeleteStudentById() throws Exception {
    when(studentDAO.findById(1L)).thenReturn(createStudentEnity());
    doNothing().when(studentDAO).delete(any(StudentEntity.class));
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/students/1"), HttpMethod.DELETE, entity, String.class);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
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

  private StudentEntity updateStudentEntity() {
    studentEntity = new StudentEntity();
    studentEntity.setId(1L);
    studentEntity.setFirstName("vinu");
    studentEntity.setLastName("V");
    studentEntity.setEmail("vinu@gmail.com");
    return studentEntity;
  }

  private StudentRequest createStudentRequest() {
    return new StudentRequest(1L, "vinu", "V", "Vinu@gmail.com", 6);
  }
}
