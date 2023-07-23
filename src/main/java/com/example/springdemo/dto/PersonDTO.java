package com.example.springdemo.dto;

import java.sql.Date;

public class PersonDTO {

    private int id;

    private String name;

    private String gender;

    private String contactno;

    private Date dob;

    private String bloodgroup;

    private String emailid;


    private String age;

    public PersonDTO(int id, String name, String gender, String contactno, Date dob, String bloodgroup, String emailid, String age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactno = contactno;
        this.dob = dob;
        this.bloodgroup = bloodgroup;
        this.emailid = emailid;
        this.age = age;
    }

    public PersonDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
