package com.demo.studentmanagement.model;

public class StudentDTO {

  private Long studentId;

  private String firstName;

  private String lastName;

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
