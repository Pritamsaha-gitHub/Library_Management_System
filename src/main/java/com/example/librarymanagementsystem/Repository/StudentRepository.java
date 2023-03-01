package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "select * from student where name =:name",nativeQuery = true)
    List<Student> findStudentByName(String name);
}
