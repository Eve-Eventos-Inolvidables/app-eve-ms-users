package com.example.appevemsusers.Service.EventManager.Exception;

import org.openapitools.model.UserDto;

public class NotAnEventManagerException extends EventManagerException {
    public NotAnEventManagerException( UserDto user) {
        super( user.getName() + " no es un event manager" );
    }
}
