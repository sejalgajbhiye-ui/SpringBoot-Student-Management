package com.springboot.Video4_a.controller;

import com.springboot.Video4_a.dto.StudentDto;
import com.springboot.Video4_a.dto.StudentRequestDto;
import com.springboot.Video4_a.entity.StudentEntity;
import com.springboot.Video4_a.repository.StudentRepository;
import com.springboot.Video4_a.service.StudentService;
import com.springboot.Video4_a.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @GetMapping("/getallstudents")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PostMapping("/addstudent")
    public ResponseEntity<StudentDto> addStudent(@RequestBody @Valid StudentRequestDto studentRequestDto){
        return ResponseEntity.ok(studentService.addStudent(studentRequestDto));
    }

    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updatestudentallinfo/{id}")
    public ResponseEntity<StudentDto> updateStudentAllInfo(@PathVariable Long id,@RequestBody @Valid StudentRequestDto studentRequestDto){
        return ResponseEntity.ok(studentService.updateStudentAllInfo(id,studentRequestDto));
    }

    @PatchMapping("/updatepartialstudent/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id,@RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id,updates));
    }
}
