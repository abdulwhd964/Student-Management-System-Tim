/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */
package com.studentmanagement.exceptionhandler;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.exception.BadCredentialsException;
import com.studentmanagement.exception.JwtException;
import com.studentmanagement.exception.UsernameNotFoundException;
import com.studentmanagement.presentation.ErrorResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GlobalExceptionHandler {

	static String SPACE = " ";

	@ExceptionHandler(value = StudentNotFoundException.class)
	ResponseEntity<ErrorResponse> handleStudentNotFoundException(final StudentNotFoundException exception) {
		log.error("error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	ResponseEntity<ErrorResponse> handleUsernameNotFoundException(final UsernameNotFoundException exception) {
		log.error("error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = JwtException.class)
	ResponseEntity<ErrorResponse> handleJwtException(final JwtException exception) {
		log.error("An error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = BadCredentialsException.class)
	ResponseEntity<ErrorResponse> handleBadCredentialsException(
			final BadCredentialsException exception) {
		log.error("An error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
			final DataIntegrityViolationException exception) {
		log.error("An error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse("Student Email Already Exist!!"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	ResponseEntity<ErrorResponse> handleConstrainViolation(final ConstraintViolationException exception) {
		log.error("An error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(buildErrors(exception).toString()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
		log.error("An error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(buildErrors(exception).toString()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	ResponseEntity<ErrorResponse> handleException(final Exception exception) {
		log.error("An unexpected error occurred: {}", exception.getMessage(), exception);
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = RuntimeException.class)
	ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException exception) {
		log.error("An unexpected error occurred: {}", exception.getMessage(), exception);
		if(exception instanceof HttpMessageNotReadableException){
			if(StringUtils.containsAnyIgnoreCase(exception.getMessage(),"localDate")){
				return new ResponseEntity<>(new ErrorResponse("Date format must be yyyy-MM-dd"), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private StringJoiner buildErrors(final ConstraintViolationException exception) {
		var errors = new StringJoiner(",");
		for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
			var propertyPath = constraintViolation.getPropertyPath().toString();
			var errorViolation = propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
			errors.add(errorViolation + SPACE + constraintViolation.getMessage());
		}
		return errors;
	}

	private StringJoiner buildErrors(final MethodArgumentNotValidException exception) {
		var errors = new StringJoiner(",");
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			errors.add(fieldError.getDefaultMessage());
		}
		return errors;
	}

}
