package com.example.appevemsusers.Service.User.Role;

import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Service.Utilities.BaseMapper;
import org.openapitools.model.CreateRoleDto;
import org.openapitools.model.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends BaseMapper<Role, RoleDto> {
    @Override
    public RoleDto toDto(Role entity) {
        if(entity == null) return null;
        RoleDto dto = new RoleDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setArchived(entity.isArchived());
        return dto;
    }

    public Role toEntity(CreateRoleDto dto) {
        if(dto == null) return null;
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }

}
