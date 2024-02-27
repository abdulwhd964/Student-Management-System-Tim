package com.studentmanagement.presentation;

/*
 * Author: Abdul Wahid
 * Date:   23 Feb 2024
 */

public class ResponseBuilder {
	private String message;
	private Object data;

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
