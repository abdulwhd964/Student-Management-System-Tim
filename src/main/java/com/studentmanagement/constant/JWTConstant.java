package com.studentmanagement.constant;
/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JWTConstant {
	JWT_TOKEN_VALID("JWT token is valid"), JWT_TOKEN_INVALID("JWT token is invalid"), JWT_TOKEN_EXPIRED("JWT token is expired"), JWT_TOKEN_MISSING_OR_EMPTY("JWT token is missing or empty");

	private String message;

}
