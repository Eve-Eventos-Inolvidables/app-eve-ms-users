package com.example.appevemsusers.Controller;

import com.example.appevecommon.Controller.BaseController;
import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Service.Utilities.Responses.Response;
import com.example.appevecommon.Service.Utilities.Responses.ResponseFactory;
import com.example.appevemsusers.Service.User.Role.RoleFilter;
import com.example.appevemsusers.Service.User.Role.RoleMapper;
import com.example.appevemsusers.Service.User.Role.RoleService;
import jakarta.validation.Valid;
import org.openapitools.model.CreateRoleDto;
import org.openapitools.model.RoleDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController<Role, RoleDto, RoleFilter, RoleMapper,RoleService> {

    protected RoleController(RoleService service) {
        super(service);
    }

    @PostMapping
    Response<RoleDto> create(@Valid @RequestBody CreateRoleDto dto){
        return ResponseFactory.ok(service.createRole(dto) );
    }

}
