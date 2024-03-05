package com.example.school.service;

import com.example.school.client.StudentClient;
import com.example.school.model.FullSchoolResponse;
import com.example.school.model.School;
import com.example.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    public void saveSchool(School student){
        schoolRepository.save(student);
    }

    public List<School> findAllSchools(){
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolWithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId).orElse(
                School.builder().name("NOT_FOUND").email("NOT_FOUND").build()
        );
        var students = studentClient.findAllStudentsBySchool(schoolId);  // find all the students from the student microservice
        return FullSchoolResponse.builder().name(school.getName()).email(school.getEmail()).students(students).build();
    }
}
