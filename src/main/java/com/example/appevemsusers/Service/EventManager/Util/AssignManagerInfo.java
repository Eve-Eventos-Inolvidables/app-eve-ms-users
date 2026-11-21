package com.example.appevemsusers.Service.EventManager.Util;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AssignManagerInfo(Boolean success, Long managerId, String message) {
}
