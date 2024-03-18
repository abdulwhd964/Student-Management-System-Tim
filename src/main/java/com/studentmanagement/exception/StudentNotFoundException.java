package com.studentmanagement.exception;

/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */
public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4742042535382558448L;

	public StudentNotFoundException(String message) {
		super(message);
	}

}
