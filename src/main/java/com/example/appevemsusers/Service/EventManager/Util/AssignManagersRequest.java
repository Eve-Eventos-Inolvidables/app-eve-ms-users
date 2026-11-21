package com.example.appevemsusers.Service.EventManager.Util;

import java.util.List;

public record AssignManagersRequest(Long eventId, List<Long> managers) {
}
