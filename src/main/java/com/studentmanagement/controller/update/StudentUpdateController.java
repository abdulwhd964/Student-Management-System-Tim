/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:10:33 PM
 */
package com.studentmanagement.controller.update;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.presentation.Response;
import com.studentmanagement.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class StudentUpdateController {
    final StudentService studentService;

    /**
     * update student
     *
     * @param studentId
     * @param studentDTO
     * @return
     */
    @Tag(name = "Update student")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Validation Error"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @Operation(summary = "update student", description = "update student")
    @PutMapping("{studentId}")
    public ResponseEntity<Response> updateStudent(@PathVariable long studentId,
                                                  @Valid @RequestBody StudentDTO studentDTO) {
        log.info("updating student");
        return new ResponseEntity<>(studentService.update(studentId, studentDTO), HttpStatus.OK);
    }
}
