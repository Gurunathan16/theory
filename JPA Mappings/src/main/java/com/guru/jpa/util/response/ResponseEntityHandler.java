package com.guru.jpa.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseEntityHandler
{
    public static ResponseEntity<Map<String, Object>> getResponseEntity(HttpStatus httpStatus, String message,
                                                                        String detailsName, Object details)
    {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("code", httpStatus.value());
        response.put("status", httpStatus);
        response.put("message", message);
        response.put(detailsName, details);

        return ResponseEntity.status(httpStatus).body(response);

    }

    public static ResponseEntity<Map<String, Object>> validationErrorBuilder(BindingResult bindingResult)
    {
        return ResponseEntityHandler.getResponseEntity(HttpStatus.BAD_REQUEST, "Validation check failed.",
                "Validation Errors", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()));
    }
}
