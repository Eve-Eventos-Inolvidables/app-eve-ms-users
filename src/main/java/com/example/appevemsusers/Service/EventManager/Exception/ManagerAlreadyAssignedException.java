package com.example.appevemsusers.Service.EventManager.Exception;

import org.openapitools.model.UserDto;

public class ManagerAlreadyAssignedException extends RuntimeException {
    public ManagerAlreadyAssignedException(UserDto userDto) {
        super(userDto.getRoleName() + " ya esta asignado/a a este evento");
    }
}
