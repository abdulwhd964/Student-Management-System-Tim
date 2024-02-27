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
	TOKEN_VALID("token is valid"), TOKEN_INVALID("token is invalid"), TOKEN_EXPIRED("token is expired");

	private String message;

}
