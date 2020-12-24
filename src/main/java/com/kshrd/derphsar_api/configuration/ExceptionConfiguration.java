package com.kshrd.derphsar_api.configuration;

import com.kshrd.derphsar_api.rest.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handle(ResponseStatusException e) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(9999);
        response.setDetail(e.getMessage());

        return ResponseEntity.ok(response);
    }

}
