package com.studentmanagement.presentation;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseBuilder {
	String message;
	Object data;

	public ResponseBuilder message(String message) {
		this.message = message;
		return this;
	}

	public ResponseBuilder data(Object data) {
		this.data = data;
		return this;
	}

	public Response build() {
		return new Response(message, data);
	}

}
