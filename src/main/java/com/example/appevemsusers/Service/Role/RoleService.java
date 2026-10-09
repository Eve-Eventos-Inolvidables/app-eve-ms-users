package com.example.appevemsusers.Service.Role;

import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Repository.IBaseRepository;
import com.example.appevecommon.Service.UpdatableService;
import org.openapitools.model.CreateRoleDto;
import org.openapitools.model.RoleDto;
import org.openapitools.model.UpdateRoleDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class RoleService extends UpdatableService<
        Role,
        RoleDto, UpdateRoleDto,RoleMapper,RoleFilter> {


    protected RoleService(IBaseRepository<Role> repository,RoleMapper mapper) {
        super(repository,mapper);
    }

    public RoleDto createRole(CreateRoleDto dto){

        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    @Override
    public List<RoleDto> getAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
//    public RoleDto updateRole(Long id, UpdateRoleDto dto){
//        Role updatedEntity = patch(id, entity -> mapper.updateFromDto(dto, entity));
//
//        return mapper.toDto(updatedEntity);
//    }
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
