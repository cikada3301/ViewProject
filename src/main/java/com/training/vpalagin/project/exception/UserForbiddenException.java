package com.training.vpalagin.project.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserForbiddenException extends ResponseStatusException {

    public UserForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    @Override
    public HttpHeaders getResponseHeaders() {
        return super.getResponseHeaders();
    }
}
