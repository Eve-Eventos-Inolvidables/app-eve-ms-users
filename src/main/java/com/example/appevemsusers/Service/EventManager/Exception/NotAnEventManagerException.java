package com.example.appevemsusers.Service.EventManager.Exception;

import org.openapitools.model.UserDto;

public class NotAnEventManagerException extends RuntimeException {
    public NotAnEventManagerException( UserDto user) {
        super( user.getName() + " no es un event manager" );
    }
}
