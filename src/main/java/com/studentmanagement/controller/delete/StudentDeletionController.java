/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:10:26 PM
 */
package com.studentmanagement.controller.delete;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.presentation.Response;
import com.studentmanagement.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class StudentDeletionController {
    
	StudentService studentService;

    /**
     * delete student
     *
     * @param studentId
     * @return
     */
    @Tag(name = "Delete student")
    @Operation(summary = "delete student", description = "Student must exist")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "delete student by Id"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @DeleteMapping("{studentId}")
    public ResponseEntity<Response> deleteStudent(@PathVariable long studentId) {
        log.info("deleting student");
        studentService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
