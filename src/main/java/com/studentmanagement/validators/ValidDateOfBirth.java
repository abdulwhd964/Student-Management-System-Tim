/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:10:48 AM
 */
package com.studentmanagement.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface ValidDateOfBirth {
	
	String message() default "Date of birth should not be less than 15 years ago";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
