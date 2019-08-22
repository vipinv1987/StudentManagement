package com.demo.studentmanagement.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StudentDTO {

  private Long studentId;

  @NotEmpty(message = "Please provide the first name")
  private String firstName;

  @NotEmpty(message = "Please provide the last name")
  private String lastName;

  @Pattern(regexp = "^(.+)@(.+)$", message = "Please provide valid Email address")
  private String emailAdress;

  private Standard standard;

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAdress() {
    return emailAdress;
  }

  public void setEmailAdress(String emailAdress) {
    this.emailAdress = emailAdress;
  }

  public Standard getStandard() {
    return standard;
  }

  public void setStandard(Standard standard) {
    this.standard = standard;
  }
}
