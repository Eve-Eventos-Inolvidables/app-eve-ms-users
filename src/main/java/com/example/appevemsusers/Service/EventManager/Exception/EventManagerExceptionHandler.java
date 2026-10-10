package com.example.appevemsusers.Service.EventManager.Exception;

import com.example.appevecommon.Service.Utilities.Responses.Error.ErrorResponse;
import com.example.appevecommon.Service.Utilities.Responses.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventManagerExceptionHandler {

    @ExceptionHandler(NotAnEventManagerException.class)
    public ResponseEntity<ErrorResponse> handleNotAnManagerException(NotAnEventManagerException ex){
        return ResponseFactory.badRequest(ex.getMessage());
    }
}
