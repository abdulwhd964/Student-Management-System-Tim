/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:9:27 PM
 */
package com.studentmanagement.controller.create;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class StudentCreationController {

    StudentService studentService;

    /**
     * save student
     *
     * @param studentDTO
     * @return
     */
    @Tag(name = "Create student")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created. The student was successfully created."),
            @ApiResponse(responseCode = "400", description = "Returned when there are validation errors in the submitted form data."),
            @ApiResponse(responseCode = "401", description = "Returned when no JWT token is provided or the provided JWT token has invalid."),
            @ApiResponse(responseCode = "500", description = "Returned when an unexpected internal server error occurs.")})
    @Operation(summary = "create student")
    @PostMapping
    public ResponseEntity<Response> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        log.info("creating student");
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);
    }

}
