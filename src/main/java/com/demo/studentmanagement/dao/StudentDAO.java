package com.demo.studentmanagement.dao;

import com.demo.studentmanagement.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<StudentEntity,Long> {
}
