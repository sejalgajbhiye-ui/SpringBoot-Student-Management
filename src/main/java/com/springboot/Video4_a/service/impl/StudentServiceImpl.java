package com.springboot.Video4_a.service.impl;

import com.springboot.Video4_a.dto.StudentDto;
import com.springboot.Video4_a.dto.StudentRequestDto;
import com.springboot.Video4_a.entity.StudentEntity;
import com.springboot.Video4_a.repository.StudentRepository;
import com.springboot.Video4_a.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentEntity> studentEntities=studentRepository.findAll();
        return studentEntities.stream()
                .map(studentEntity -> modelMapper.map(studentEntity,StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getById(Long id) {
        StudentEntity studentEntity=studentRepository.findById(id).orElseThrow();
        return modelMapper.map(studentEntity,StudentDto.class);
    }

    @Override
    public StudentDto addStudent(StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity=modelMapper.map(studentRequestDto,StudentEntity.class);
        StudentEntity studentEntity1=studentRepository.save(studentEntity);
        return modelMapper.map(studentEntity,StudentDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("student not exists with this id");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudentAllInfo(Long id, StudentRequestDto studentRequestDto) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("student not exists with this id");
        }
        StudentEntity studentEntity=studentRepository.findById(id).orElseThrow();
        modelMapper.map(studentRequestDto,studentEntity);
        StudentEntity studentEntity1=studentRepository.save(studentEntity);
        return modelMapper.map(studentEntity1,StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("student not exists with this id");
        }
        StudentEntity studentEntity=studentRepository.findById(id).orElseThrow();
        for(Map.Entry<String,Object> entry:updates.entrySet()){
            if(entry.getKey().equals("name")){
                studentEntity.setName((String)entry.getValue());
            }else if(entry.getKey().equals("email")){
                studentEntity.setEmail((String)entry.getValue());
            }
        }
        studentEntity=studentRepository.save(studentEntity);
        return modelMapper.map(studentEntity,StudentDto.class);
    }
}
