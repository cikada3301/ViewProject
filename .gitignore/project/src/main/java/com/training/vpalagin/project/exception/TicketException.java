package com.training.vpalagin.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TicketException extends Exception {
    public TicketException(String message) {
        super(message);
    }
}