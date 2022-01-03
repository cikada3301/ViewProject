package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.exception.TicketException;
import com.training.vpalagin.project.logger.TicketLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//TODO
@RestControllerAdvice
public class TicketExceptionHandler {

    @ExceptionHandler(TicketException.class)
    public ResponseEntity<?> resourceNotFoundException(TicketException ticketException, WebRequest request) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @TicketLogger
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
