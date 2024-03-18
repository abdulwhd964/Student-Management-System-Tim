package com.studentmanagement.exception;
/*
 * Author: Abdul Wahid
 * Date:   18 Mar 2024
 */
public class UsernameNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3901958000889411668L;

	public UsernameNotFoundException(String message) {
        super(message);
    }

}

