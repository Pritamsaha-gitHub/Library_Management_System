package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.Converters.StudentConverter;
import com.example.librarymanagementsystem.DTOs.StudentEntryDto;
import com.example.librarymanagementsystem.Enums.Cardstatus;
import com.example.librarymanagementsystem.Models.Card;
import com.example.librarymanagementsystem.Models.Student;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import com.example.librarymanagementsystem.ResponseDtos.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addNewStudent(StudentEntryDto studentEntryDto){
        Student student= StudentConverter.DtoToEntity(studentEntryDto);
        //setting card
        Card card1=Card.builder()
                .student(student)
                .cardstatus(Cardstatus.ACTIVATED)
                .build();
        //adding card in student
        student.setCard(card1);
        studentRepository.save(student);
        return "New Student and Card sucessfully added";
    }
    public String changeEmail(int studentId,String email) throws Exception{
        Student student=studentRepository.findById(studentId).get();
        student.setEmail(email);
        studentRepository.save(student);
        return "Email Sucessfully Updated";
    }
    public String changeMobileNo(int studentId, String mobNo) throws Exception{
        Student student=studentRepository.findById(studentId).get();
        student.setMobNo(mobNo);
        studentRepository.save(student);
        return "MobileNo Updated Sucessfully";
    }
    public StudentResponseDto studentDetails(int studentId) throws Exception{
        Student student=studentRepository.findById(studentId).get();
        if(student.equals(null)){
            throw  new Exception("Student Not found");
        }
        StudentResponseDto newStudent=StudentConverter.entityToResponse(student);
        return newStudent;
    }
    public List<StudentResponseDto> studentByName(String name)throws Exception{
        List<Student> allstudent=studentRepository.findStudentByName(name);
        List<StudentResponseDto> studentList=StudentConverter.entryToResponseList(allstudent);
        if(studentList.size()==0){
            throw  new Exception("Student Not found");
        }
        return studentList;
    }
}
