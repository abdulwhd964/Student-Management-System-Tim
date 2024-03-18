/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:10:33 PM
 */
package com.studentmanagement.controller.update;

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
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StudentUpdateController {
	StudentService studentService;

	/**
	 * update student
	 *
	 * @param studentId
	 * @param studentDTO
	 * @return
	 */
	@Tag(name = "Update student")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK. The operation was successful."),
			@ApiResponse(responseCode = "400", description = "Returned when there are validation errors in the submitted form data."),
			@ApiResponse(responseCode = "401", description = "Returned when JWT authentication is enabled, and either no JWT token is provided or the provided JWT token has invalid."),
			@ApiResponse(responseCode = "500", description = "Returned when an unexpected internal server error occurs.") })
	@Operation(summary = "update student", description = "update student")
	@PutMapping("{studentId}")
	public ResponseEntity<Response> updateStudent(@PathVariable long studentId,
			@Valid @RequestBody StudentDTO studentDTO) {
		log.info("updating student");
		return new ResponseEntity<>(studentService.update(studentId, studentDTO), HttpStatus.OK);
	}
}
