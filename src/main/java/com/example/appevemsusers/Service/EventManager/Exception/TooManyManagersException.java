package com.example.appevemsusers.Service.EventManager.Exception;

public class TooManyManagersException extends EventManagerException {
    public TooManyManagersException() {
        super("Se ha alcanzado el limite de managers para este evento");
    }
}
