package com.training.vpalagin.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TicketExceptionInternalError extends ResponseStatusException {
    public TicketExceptionInternalError(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
