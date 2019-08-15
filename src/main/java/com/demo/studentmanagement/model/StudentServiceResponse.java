package com.demo.studentmanagement.model;

public class StudentServiceResponse {

    private String firstName;
    private String lastName;

    private String emailadress;
    private Integer standard;

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

    public StudentServiceResponse(String firstName, String lastName, String emailadress, Integer standard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailadress = emailadress;
        this.standard = standard;
    }


}
