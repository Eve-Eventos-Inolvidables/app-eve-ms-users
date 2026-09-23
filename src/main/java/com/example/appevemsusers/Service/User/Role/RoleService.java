package com.example.appevemsusers.Service.User.Role;

import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Repository.IBaseRepository;
import com.example.appevecommon.Service.AbstractBaseService;
import org.openapitools.model.RoleDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractBaseService<Role, RoleDto, RoleFilter> {


    protected RoleService(IBaseRepository<Role> repository) {
        super(repository);
    }

    @Override
    public RoleDto toDto(Role entity) {
        if(entity == null)return null;
        RoleDto roleDto =new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
        return roleDto;
    }

    @Override
    public Role toEntity(RoleDto dto) {
        if(dto == null) return null;
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    @Override
    public Specification<Role> toSpecification(RoleFilter filter) {
        return (root, query, criteriaBuilder) -> {
            if(filter == null) return criteriaBuilder.conjunction(); //Return 1=1(true) if there isn't any filter
            List<Predicate> predicateList =new ArrayList<>();
            if(filter.getName() != null  && !filter.getName().isBlank() ) {
                predicateList.add(
                       criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")) , "%" + filter.getName().toLowerCase() + "%"
                        )
                );
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
