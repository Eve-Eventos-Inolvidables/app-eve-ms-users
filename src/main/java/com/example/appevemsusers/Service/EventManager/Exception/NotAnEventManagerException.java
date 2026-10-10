package com.example.appevemsusers.Service.EventManager.Exception;

import com.example.appevecommon.Models.User.User;

public class NotAnEventManagerException extends RuntimeException {
    public NotAnEventManagerException( User user) {
        super( user.getName() + " no es un event manager" );
    }
}
