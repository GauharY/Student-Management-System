package com.example.day3sms.controller;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.service.StudentService;
import com.example.day3sms.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;
    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }
    private void checkToken(String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid Token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

//    create function API
    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.addStudent(student);
    }
//    Display Students
    @GetMapping("/students")
    public List<StudentResponseDto> getStudents(@RequestHeader(value = "Authorization", required = false) String authHeader){
        checkToken(authHeader);
        return service.getStudents();
    }

//    Upadte Students
    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id, @RequestBody StudentRequestDto student){
        return service.updateStudent(id, student);
    }

//    Delete Students
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return "Student Deleted Successfully";
    }
//Patch Mappings
    @PatchMapping("/update/{id}")
    public StudentResponseDto patchStudent(@PathVariable String id, @RequestBody StudentRequestDto dto) {
        return service.patchStudent(id, dto);
    }
}
