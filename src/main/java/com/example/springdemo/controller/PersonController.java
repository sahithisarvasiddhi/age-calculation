package com.example.springdemo.controller;

import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.model.Person;

import com.example.springdemo.repository.PersonRepository;
import com.example.springdemo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RestController
@RestControllerAdvice
public class PersonController {


    @Autowired
    PersonRepository personRepository;


    @Autowired
    PersonService personService;


    //get list of persons for given blood group
    @GetMapping("/bloodgroup/{bloodGroup}")
    public ResponseEntity<?> getPersonsByBloodGroup(@PathVariable String bloodGroup) {
        List<Person> persons = personService.getPersonsByBloodGroup(bloodGroup);
        if (persons.isEmpty()) {
            String errorMessage = "Persons with this Bloodgroup" + bloodGroup + "' not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } else {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }
    }

    // get Donor for given blood group
    @GetMapping("/blood-donors/{bloodGroup}")
    public ResponseEntity<?> getDonorsByBloodGroup(@Valid @PathVariable("bloodGroup") String bloodGroup) {
        List<Person> donors = personService.getPersonsByBloodGroup(bloodGroup);
        if (donors.isEmpty()) {
            String errorMessage = "Donors for this Bloodgroup " + bloodGroup + "' not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.ok(donors);
    }

    //calculate Age for given name
    @GetMapping("/age/{name}")
    public ResponseEntity<?> getAgeByName(@PathVariable String name) {
        List<Person> person = (List<Person>) personRepository.findByNameGivenByName(name);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with name '" + name + "' not found.");
        }
      List  <PersonDTO> ageByName = personService.getAgeByName(name);
        return ResponseEntity.ok(ageByName);
    }


    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id) {
//            Person person = personRepository.findById(id);
        if (!personRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with Id " + id + " not found....!");
        }
        personRepository.deleteById(id);
        return ResponseEntity.ok("Person Details Deleted");
    }

    @PostMapping("/add")
    public ResponseEntity<?> createPerson(@RequestBody @Valid Person person) {
        String existingPerson = personService.findByEmail(person.getEmailid());
        List<Person> personList = personRepository.findAll();
        for (Person p : personList) {
            if (existingPerson.equals(p.getEmailid())) {
                return ResponseEntity.badRequest().body("Person with this email already exists.");
            }
        }
        Person createdPerson = personService.createPerson(person);

        if (!isValidGender(person.getGender())) {
            return ResponseEntity.badRequest().body("Type valid gender...");
        }
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

        private boolean isValidGender(String gender) {
        if (gender == null || !gender.matches("(?i)^(female|male|other)$")) {
            return false;
        } else {
            return true;
        }
    }

}




