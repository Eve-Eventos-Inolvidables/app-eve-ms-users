package com.example.appevemsusers.Service.User.Role;

import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Service.Utilities.UpdatableMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.CreateRoleDto;
import org.openapitools.model.RoleDto;
import org.openapitools.model.UpdateRoleDto;

@Mapper(componentModel = "spring")
public abstract class RoleMapper implements UpdatableMapper<Role,RoleDto,UpdateRoleDto> {

    @Override
    public RoleDto toDto(Role entity) {
        if (entity == null) return null;
        RoleDto dto = new RoleDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setArchived(entity.isArchived());
        return dto;
    }

    public Role toEntity(CreateRoleDto dto) {
        if (dto == null) return null;
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(UpdateRoleDto dto, @MappingTarget Role role);
}