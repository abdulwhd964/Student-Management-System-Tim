/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:11:33 AM
 */
package com.studentmanagement.presentation;

public class Response {

    private final String message;
    private final Object data;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
