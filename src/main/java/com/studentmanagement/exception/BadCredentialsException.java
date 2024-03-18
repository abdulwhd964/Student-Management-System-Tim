/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:2:12 PM
 */
package com.studentmanagement.exception;

public class BadCredentialsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5770135955393957511L;

	public BadCredentialsException(String message) {
        super(message);
    }

}
