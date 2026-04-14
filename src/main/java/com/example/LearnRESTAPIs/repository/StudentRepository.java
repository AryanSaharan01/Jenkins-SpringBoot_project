package com.example.LearnRESTAPIs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LearnRESTAPIs.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);

}

//Student → kaunsi entity handle karni hai
//Long → primary key ka type kya hai
