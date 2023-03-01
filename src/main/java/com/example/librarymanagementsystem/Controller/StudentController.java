package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTOs.StudentEntryDto;
import com.example.librarymanagementsystem.Models.Student;
import com.example.librarymanagementsystem.ResponseDtos.StudentResponseDto;
import com.example.librarymanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add_student")
    public ResponseEntity<String> addNewStudent(@RequestBody() StudentEntryDto studentEntryDto){
        String ans= studentService.addNewStudent(studentEntryDto);
        return new ResponseEntity<>(ans, HttpStatus.CREATED);
    }
    @PutMapping("/change_email")
    public ResponseEntity<String> changeEmail(@RequestParam("id") int studentId,@RequestParam String email){
        try {
            String ans=studentService.changeEmail(studentId,email);
            return new ResponseEntity<>(ans, HttpStatus.ACCEPTED);
        }catch (Exception e){
            String ans="Student not found";
            return new ResponseEntity<>(ans, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/change_mobNo")
    public ResponseEntity<String> changeMobileNo(@RequestParam("id") int studentId, @RequestParam String mobNo){
        try {
            String ans=studentService.changeMobileNo(studentId,mobNo);
            return new ResponseEntity<>(ans, HttpStatus.ACCEPTED);
        }catch (Exception e){
            String ans="Student not found";
            return new ResponseEntity<>(ans, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findById")
    public ResponseEntity<StudentResponseDto> studentDetails(@RequestParam("id") int studentId){
        try{
            StudentResponseDto student=studentService.studentDetails(studentId);
            return new ResponseEntity<>(student,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find_students_by_name")
    public ResponseEntity<List<StudentResponseDto>> studentByName(@RequestParam("name") String name){
        try{
            List<StudentResponseDto> allStudent=studentService.studentByName(name);
            return new ResponseEntity<>(allStudent,HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
