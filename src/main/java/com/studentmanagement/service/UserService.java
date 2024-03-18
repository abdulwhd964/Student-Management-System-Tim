/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:1:42 PM
 */
package com.studentmanagement.service;

import org.springframework.stereotype.Service;

import com.studentmanagement.dto.UserDTO;
import com.studentmanagement.exception.BadCredentialsException;
import com.studentmanagement.exception.UsernameNotFoundException;
import com.studentmanagement.presentation.Response;
import com.studentmanagement.presentation.ResponseBuilder;
import com.studentmanagement.repo.UserRepository;
import com.studentmanagement.util.jwt.JwtTokenUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {

	UserRepository userRepository;

	JwtTokenUtil jwtTokenUtil;

	public Response validate(final UserDTO userDTO) {
		validateUsername(userDTO.getUserName());
		validatePassword(userDTO.getPassword());
		return new ResponseBuilder().message("login_success").data(jwtTokenUtil.generateToken(userDTO.getUserName()))
				.build();
	}

	private void validateUsername(final String userName) {
		userRepository.findByUserName(userName).orElseThrow(
				() -> new UsernameNotFoundException(String.format("username: %s. not found", userName)));
	}

	private void validatePassword(final String password) {
		userRepository.findByPassword(password)
				.orElseThrow(() -> new BadCredentialsException("Invalid Password"));
	}
	
}
