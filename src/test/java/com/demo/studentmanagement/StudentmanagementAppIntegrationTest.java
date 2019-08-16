package com.demo.studentmanagement;

import com.demo.studentmanagement.dao.StudentDAO;
import com.demo.studentmanagement.model.StudentEntity;
import com.demo.studentmanagement.model.StudentWebResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentmanagementAppIntegrationTest {

  @LocalServerPort private int port;

  @MockBean StudentDAO studentDAO;

  Optional<StudentEntity> student;

  StudentEntity studentEntity;

  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @Test
  public void testStudentDetails() throws Exception {
    when(studentDAO.findById(1L)).thenReturn(createStudentEnity());
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/students/1"), HttpMethod.GET, entity, String.class);
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
}
