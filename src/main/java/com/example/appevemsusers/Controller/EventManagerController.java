package com.example.appevemsusers.Controller;

import com.example.appevecommon.Controller.BaseController;
import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Service.Utilities.Responses.Response;
import com.example.appevecommon.Service.Utilities.Responses.ResponseFactory;
import com.example.appevemsusers.Service.EventManager.EventManagerFilter;
import com.example.appevemsusers.Service.EventManager.EventManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.openapitools.model.CreateEventManagerDto;
import org.openapitools.model.EventManagerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Event Manager")
@RestController
@RequestMapping("/api/managers")
public class EventManagerController extends BaseController
        <
        EventManager,
        EventManagerDto,
        EventManagerFilter,
        EventManagerService
                >
{
    protected EventManagerController(EventManagerService service) {
        super(service);
    }

    @PostMapping
    public Response<EventManagerDto> create(@Valid @RequestBody CreateEventManagerDto dto){
        return ResponseFactory.resourceCreated("Event Manager asignado correctamente", service.createEventManager(dto));
    }

}
