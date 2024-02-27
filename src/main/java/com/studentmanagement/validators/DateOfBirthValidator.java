/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:10:49 AM
 */
package com.studentmanagement.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
	@Override
	public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
		if (dateOfBirth == null) {
			return true;
		}
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(dateOfBirth, currentDate);

		// Check if the age is at least 15 years
		return period.getYears() >= 15;
	}
}
