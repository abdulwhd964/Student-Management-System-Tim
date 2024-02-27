/**
 * Author: Abdul Wahid
 * Date:25/02/2024
 * Time:10:59 AM
 */
package com.studentmanagement.service;

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.presentation.Response;

public interface StudentService {

    Response findAll();

    Response findStudent(long studentId);

    Response save(StudentDTO studentDTO);

    Response update(long studentId,StudentDTO studentDTO);

    void delete(long studentId);

}
