package com.example.springdemo.service;

import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.model.Person;
import com.example.springdemo.repository.PersonRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class PersonService {


    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public String findByEmail(String emailid) {
        return emailid;
    }


    public List<Person> getPersonsByBloodGroup(String bloodGroup) {
        return personRepository.findByBloodgroupIgnoreCase(bloodGroup);
    }
//----------------------------------------------------------------------------------------------------------------------

    public List<PersonDTO> getAgeByName(String name) {
        List<Person> personsList = personRepository.findByNameGivenByName(name);
        List<PersonDTO> persons = new ArrayList<>();
        if (personsList == null) {
            return null;
        }
        for (Person person : personsList) {
            Date dob = person.getDob();
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDate = dob.toLocalDate();
            Period period = Period.between(birthDate, currentDate);

            PersonDTO personDTO = new PersonDTO();
            personDTO.setName(person.getName());
            personDTO.setGender(person.getGender());
            personDTO.setDob(person.getDob());
            personDTO.setBloodgroup(person.getBloodgroup());
            personDTO.setContactno(person.getContactno());
            personDTO.setEmailid(person.getEmailid());
            personDTO.setAge(String.format("%d years %d months %d days",
                    period.getYears(), period.getMonths(), period.getDays()));
            persons.add(personDTO);
        }
        return persons;
    }


        //------------------------------------------------------------------------------------------------------------------
        public List<Person> getDonorsByBloodGroup (String bloodGroup){
            List<Person> donorPersons = personRepository.getCompatibleDonors(bloodGroup);
            return donorPersons;
        }
        //------------------------------------------------------------------------------------------------------------------


}