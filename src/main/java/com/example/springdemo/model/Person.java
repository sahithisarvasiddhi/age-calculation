package com.example.springdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Invalid name : Name is mandatory")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Exceeds 30 characters")
    private String name;

    @Column
    @NotBlank(message = "Invalid name : Specify gender")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid")
    private String gender;

    @Column
    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String contactno;

    @Column(name = "Dob")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(name = "BloodGroup")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$",message ="Invalid Blood Group")
    private String bloodgroup;

    @Column(name = "Email")
    @Email(message = "Type a valid Email ")
    private String emailid;


}