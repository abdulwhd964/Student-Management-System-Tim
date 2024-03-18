package com.studentmanagement.controller;

import com.studentmanagement.dto.UserDTO;
import com.studentmanagement.presentation.Response;
import com.studentmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LoginController {

    UserService userService;

    @Tag(name = "Login")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK. The operation was successful."),
            @ApiResponse(responseCode = "400", description = "Returned when there are validation errors in the submitted form data."),
            @ApiResponse(responseCode = "404", description = "Returned when the provided username is not found."),
            @ApiResponse(responseCode = "401",  description = "Returned when the provided password is incorrect."),
            @ApiResponse(responseCode = "500", description = "Returned when an unexpected internal server error occurs.")})
    @Operation(summary = "login to access the resource")
    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody UserDTO userDTO) {
        log.info("Attempting to login user: " + userDTO.getUserName());
        return new ResponseEntity<>(userService.validate(userDTO), HttpStatus.OK);
    }
}
