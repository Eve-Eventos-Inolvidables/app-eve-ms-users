package com.example.appevemsusers.Service.User.Role;

import com.example.appevecommon.Service.Utilities.Responses.PagedFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleFilter extends PagedFilter {
    private String name;
}
