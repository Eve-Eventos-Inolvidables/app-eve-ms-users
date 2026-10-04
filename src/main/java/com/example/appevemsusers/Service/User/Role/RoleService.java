package com.example.appevemsusers.Service.User.Role;

import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Repository.IBaseRepository;
import com.example.appevecommon.Service.AbstractBaseService;
import org.openapitools.model.CreateRoleDto;
import org.openapitools.model.RoleDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractBaseService<Role, RoleDto, RoleMapper,RoleFilter> {


    protected RoleService(IBaseRepository<Role> repository,RoleMapper mapper) {
        super(repository,mapper);
    }

    public RoleDto createRole(CreateRoleDto dto){
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
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
