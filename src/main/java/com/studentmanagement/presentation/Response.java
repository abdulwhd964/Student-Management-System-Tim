/**
 * Author: Abdul Wahid
 * Date:24/02/2024
 * Time:11:33 AM
 */
package com.studentmanagement.presentation;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Response {

    String message;
    Object data;

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
