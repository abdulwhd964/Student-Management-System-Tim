/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:1:42 PM
 */
package com.studentmanagement.service;

import com.studentmanagement.util.jwt.JwtTokenUtil;
import org.springframework.stereotype.Service;

import com.studentmanagement.dto.UserDTO;
import com.studentmanagement.exception.UserAndPasswordNotFoundException;
import com.studentmanagement.presentation.Response;
import com.studentmanagement.presentation.ResponseBuilder;
import com.studentmanagement.repo.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

	final UserRepository userRepository;

	final JwtTokenUtil jwtTokenUtil;

	public Response validate(final UserDTO userDTO) {
		validateUsername(userDTO.getUserName());
		validatePassword(userDTO.getPassword());
		return new ResponseBuilder().message("login_success").data(jwtTokenUtil.generateToken(userDTO.getUserName()))
				.build();
	}

	private void validateUsername(final String userName) {
		userRepository.findByUserName(userName).orElseThrow(
				() -> new UserAndPasswordNotFoundException(String.format("username: %s. not found", userName)));
	}

	private void validatePassword(final String password) {
		userRepository.findByPassword(password)
				.orElseThrow(() -> new UserAndPasswordNotFoundException("Invalid Password"));
	}
}
