package com.example.librarymanagementsystem.Converters;

import com.example.librarymanagementsystem.DTOs.StudentEntryDto;
import com.example.librarymanagementsystem.Enums.Cardstatus;
import com.example.librarymanagementsystem.Models.Card;
import com.example.librarymanagementsystem.Models.Student;
import com.example.librarymanagementsystem.ResponseDtos.StudentResponseDto;

import java.util.ArrayList;
import java.util.List;

public class StudentConverter {
    public static Student DtoToEntity(StudentEntryDto studentEntryDto){

        Student student=Student.builder()
                .name(studentEntryDto.getName())
                .email(studentEntryDto.getEmail())
                .mobNo(studentEntryDto.getMobNo())
                .age(studentEntryDto.getAge())
                .country(studentEntryDto.getCountry())
                .build();
        return student;
    }
    public static StudentResponseDto entityToResponse(Student student){
        Card card=student.getCard();
        StudentResponseDto newStudent=StudentResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .mobNo(student.getMobNo())
                .age(student.getAge())
                .country(student.getCountry())
                .cardId(card.getId())
                .cardstatus(card.getCardstatus())
                .build();
        return newStudent;
    }
    public static List<StudentResponseDto> entryToResponseList(List<Student> students){
        List<StudentResponseDto> studentList=new ArrayList<>();
        for (Student s: students){
            StudentResponseDto studentResponseDto=entityToResponse(s);
            studentList.add(studentResponseDto);
        }
        return studentList;
    }
}
