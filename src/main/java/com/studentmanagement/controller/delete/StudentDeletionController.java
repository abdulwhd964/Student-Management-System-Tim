/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:10:26 PM
 */
package com.studentmanagement.controller.delete;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No content. The student was successfully deleted."),
			@ApiResponse(responseCode = "404", description = "Returned when the provided Student id is not found."),
			@ApiResponse(responseCode = "401", description = "Returned when JWT authentication is enabled, and either no JWT token is provided or the provided JWT token has invalid."),
			@ApiResponse(responseCode = "500", description = "Returned when an unexpected internal server error occurs.") })
	@DeleteMapping("{studentId}")
	public ResponseEntity<Response> deleteStudent(@PathVariable long studentId) {
		log.info("deleting student");
		studentService.delete(studentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
