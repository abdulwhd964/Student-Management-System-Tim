/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */
package com.studentmanagement.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class ErrorResponse {
	private String message;
}
