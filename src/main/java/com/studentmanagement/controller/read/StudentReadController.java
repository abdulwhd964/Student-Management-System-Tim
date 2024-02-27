/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:10:22 PM
 */
package com.studentmanagement.controller.read;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentReadController {

    final StudentService studentService;

    /**
     * get all students
     *
     * @return
     */
    @Tag(name = "Get All students")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @Operation(summary = "get all students", description = "Getting all students")
    @GetMapping
    public ResponseEntity<Response> getAllStudents() {
        log.info("retrieve all student");
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    /**
     * get student
     *
     * @param studentId
     * @return
     */
    @Tag(name = "Get student")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @Operation(summary = "get student", description = "Get student")
    @GetMapping("{studentId}")
    public ResponseEntity<Response> getBook(@PathVariable Long studentId) {
        log.info("retreive student");
        return new ResponseEntity<>(studentService.findStudent(studentId), HttpStatus.OK);
    }

}
