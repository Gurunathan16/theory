package com.guru.jpa.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import com.guru.jpa.util.response.ResponseEntityHandler;

@RestControllerAdvice
public class GenericExceptionHandler
{

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEntry(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Duplicate Entry found.", "Recovery", "Try adding different entry. Not the same one 🙃." + ex.getMessage());
    }

}
