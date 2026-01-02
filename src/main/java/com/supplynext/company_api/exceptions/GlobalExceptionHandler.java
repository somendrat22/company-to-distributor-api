package com.supplynext.company_api.exceptions;

import org.hibernate.tool.schema.spi.SchemaTruncator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllExceptions(Exception e){
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", e.getMessage());
        return new ResponseEntity<>(
                resp,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity handleAllExceptions(UnAuthorizedException e){
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", e.getMessage());
        return new ResponseEntity<>(
                resp,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
