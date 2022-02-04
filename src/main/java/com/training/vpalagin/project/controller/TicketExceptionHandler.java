package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.exception.TicketExceptionInternalError;
import com.training.vpalagin.project.exception.UserForbiddenException;
import com.training.vpalagin.project.logger.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.ArrayList;

//TODO
@RestControllerAdvice
public class TicketExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Logger
    public ResponseEntity<List<String>> globalExceptionBadRequest(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(TicketExceptionInternalError.class)
    @Logger
    public ResponseEntity<String> globalExceptionHandler(TicketExceptionInternalError ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserForbiddenException.class)
    @Logger
    public ResponseEntity<String> autorizationExceptionHandler(UserForbiddenException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }
}
