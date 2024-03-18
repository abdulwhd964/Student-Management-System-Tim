/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:2:27 PM
 */
package com.studentmanagement.exception;

public class JwtException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -479776770542574067L;

	public JwtException(String message) {
		super(message);
	}
}
