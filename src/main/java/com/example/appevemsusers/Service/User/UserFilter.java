package com.example.appevemsusers.Service.User;

import com.example.appevecommon.Service.Utilities.PagedFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter extends PagedFilter{
        private String name;
}
