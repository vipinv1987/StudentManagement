package com.demo.studentmanagement.model;

public class StudentRequest {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String emailadress;
    private Integer standard;

    public StudentRequest(Long studentId, String firstName, String lastName, String emailadress, Integer standard) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailadress = emailadress;
        this.standard = standard;
    }

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

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }
}
