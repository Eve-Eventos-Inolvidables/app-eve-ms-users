package com.example.appevemsusers.Controller;

import com.example.appevecommon.Controller.BaseController;
import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.Utilities.Responses.Response;
import com.example.appevecommon.Service.Utilities.Responses.ResponseFactory;
import com.example.appevemsusers.Service.User.UserFilter;
import com.example.appevemsusers.Service.User.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.openapitools.model.CreateUserDto;
import org.openapitools.model.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag( name = "Users", description = "Controller for users ")
public class UserController extends BaseController<User, UserDto, UserFilter, UserService> {
    protected UserController(UserService userService){super(userService);}

    @PostMapping
    public Response<UserDto> create(@Valid @RequestBody CreateUserDto dto){
        return ResponseFactory.resourceCreated(service.createUser(dto));
    }
}
