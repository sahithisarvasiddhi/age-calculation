package com.example.springdemo.repository;

import com.example.springdemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByBloodgroupIgnoreCase(String bloodGroup);

    Person findByName(String name);

    @Query("From Person where emailid = :emailid")
    Person findByEmail(String emailid);

    @Query("FROM Person where name = :name")
      List <Person> findByNameGivenByName(@Param("name")String name);

    @Query("SELECT p FROM Person p WHERE  (" +
            "(:recipientBloodGroup = 'O+' AND p.bloodgroup IN ('O+', 'O-')) " +
            "OR (:recipientBloodGroup = 'O-' AND p.bloodgroup = 'O-') " +
            "OR (:recipientBloodGroup = 'A+' AND p.bloodgroup IN ('A+', 'A-', 'O+', 'O-')) " +
            "OR (:recipientBloodGroup = 'A-' AND p.bloodgroup IN ('A-', 'O-')) " +
            "OR (:recipientBloodGroup = 'B+' AND p.bloodgroup IN ('B+', 'B-', 'O+', 'O-')) " +
            "OR (:recipientBloodGroup = 'B-' AND p.bloodgroup IN ('B-', 'O-')) " +
            "OR (:recipientBloodGroup = 'AB+' AND true) " +
            "OR (:recipientBloodGroup = 'AB-' AND p.bloodgroup IN ('AB-', 'A-', 'B-', 'O-')) " +
            "OR (:recipientBloodGroup NOT IN ('O+', 'O-', 'A+', 'A-', 'B+', 'B-', 'AB+', 'AB-')))")
    List<Person> getCompatibleDonors(@Param("recipientBloodGroup") String recipientBloodGroup);
}