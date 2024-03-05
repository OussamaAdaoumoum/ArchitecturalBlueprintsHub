package com.example.school.controller;


import com.example.school.client.StudentClient;
import com.example.school.model.FullSchoolResponse;
import com.example.school.model.School;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveSchool(@RequestBody School school){
        schoolService.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools(){
        return ResponseEntity.ok(schoolService.findAllSchools());
    }

    @GetMapping("withStudents/{schoolId}")
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudents(
            @PathVariable("schoolId") Integer schoolId
    ){
        return ResponseEntity.ok(schoolService.findSchoolWithStudents(schoolId));
    }

}
