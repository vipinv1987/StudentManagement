package com.demo.studentmanagement.service;

import com.demo.studentmanagement.model.StudentDTO;

/**
 * To perform the Student creation,updation,deletion and selection
 */
public interface StudentService {
  /**
   * Get the student details by given studentId
   *
   * @param studentId
   * @return StudentDTO
   */
  public StudentDTO getById(Long studentId);

  /**
   * Create the new student
   *
   * @param studentDTO
   * @return
   */
  public StudentDTO createStudent(StudentDTO studentDTO);

  /**
   * Update the existing student by given student information
   *
   * @param studentDTO
   * @return
   */
  public StudentDTO updateById(StudentDTO studentDTO);

  /**
   * Delete the existing student by given student Id
   *
   * @param studentId
   */
  public void deleteById(Long studentId);
}
