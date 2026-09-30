package com.example.appevemsusers.Controller;

import com.example.appevecommon.Controller.BaseController;
import com.example.appevecommon.Models.User.Role;
import com.example.appevemsusers.Service.User.Role.RoleFilter;
import com.example.appevemsusers.Service.User.Role.RoleService;
import org.openapitools.model.RoleDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController<Role, RoleDto, RoleFilter, RoleService> {

    protected RoleController(RoleService service) {
        super(service);
    }

}
