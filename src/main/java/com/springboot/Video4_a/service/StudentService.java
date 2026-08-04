package com.springboot.Video4_a.service;

import com.springboot.Video4_a.dto.StudentDto;
import com.springboot.Video4_a.dto.StudentRequestDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getById(Long id);
    StudentDto addStudent(StudentRequestDto studentRequestDto);

    void deleteById(Long id);

    StudentDto updateStudentAllInfo(Long id, StudentRequestDto studentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
