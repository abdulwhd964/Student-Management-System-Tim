/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:1:39 PM
 */
package com.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

	@NotBlank(message = "username is required")
	String userName;
	
	@NotBlank(message = "password is required")
	String password;

}
