/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:9:36 PM
 */
package com.studentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.studentmanagement.validators.ValidDateOfBirth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

	long id;

	@Email(message = "Invalid email format. Please provide a valid email address. ")
	@NotBlank(message = "email is required")
	String email;

	@NotBlank(message = "student name is required")
	String name;

	@Min(value = 15, message = "Age must be at least 15")
	@NotNull(message = "student age is required")
	int age;

	@ValidDateOfBirth
	@NotNull(message = "Date of birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate dateOfBirth;

}
