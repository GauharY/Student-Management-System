package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

//    //    create
//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }
//
////    Display Student
//    public List<StudentModel> getStudents(){
//        return repository.findAll();
//    }
////    Update
//    public StudentModel updateStudent(String id, StudentModel student){
//        StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
//        existingStudent.setName(student.getName());
//        existingStudent.setAge(student.getAge());
//        existingStudent.setEmail(student.getEmail());
//        return repository.save(existingStudent);
//
//    }
//
////    Delete
//    public void deleteStudent(String id){
//        StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
//        repository.deleteById(existingStudent.getId());
//    }
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }
    public List<StudentResponseDto> getStudents(){
        return repository.findAll().stream().map(s -> new StudentResponseDto(
                    s.getId(),
                    s.getName(),
                    s.getAge(),
                    s.getEmail()
                )).toList();
    }
    // UPDATE
    public StudentResponseDto updateStudent(String id, StudentRequestDto dto) {

        StudentModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        existing.setName(dto.getName());
        existing.setAge(dto.getAge());
        existing.setEmail(dto.getEmail());

        StudentModel updated = repository.save(existing);

        return new StudentResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }

    // DELETE
    public void deleteStudent(String id) {

        StudentModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        repository.delete(existing);
    }

//    PATCH
    public StudentResponseDto patchStudent(String id, StudentRequestDto dto) {

        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        if (dto.getName() != null)
            student.setName(dto.getName());

        if (dto.getAge() != 0)
            student.setAge(dto.getAge());

        if (dto.getEmail() != null)
            student.setEmail(dto.getEmail());

        StudentModel updated = repository.save(student);

        return new StudentResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }
}
