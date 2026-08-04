package com.springboot.Video4_a.repository;

import com.springboot.Video4_a.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
