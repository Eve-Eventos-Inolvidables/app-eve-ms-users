package com.example.appevemsusers.Service.User;

import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.AbstractBaseService;
import com.example.appevecommon.Service.UpdatableService;
import com.example.appevemsusers.Repository.IUserRepository;
import com.example.appevemsusers.Service.Role.RoleService;
import jakarta.persistence.criteria.Predicate;
import org.openapitools.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends UpdatableService<User, UserDto, UpdateUserDto,UserMapper, UserFilter > {

    public UserService(IUserRepository repository ,UserMapper userMapper) {
        super(repository,userMapper);
    }

    public UserDto createUser(CreateUserDto dto){
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Specification<User> toSpecification(UserFilter f) {
        return (root, query, criteriaBuilder) -> {

            if(f == null) return criteriaBuilder.conjunction();
            List<Predicate> predicateList =new ArrayList<>();
            // Basic filter by name (searchParam)
            if(f.getName() != null  && !f.getName().isBlank() ) {
                predicateList.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")) , "%" + f.getName().toLowerCase() + "%"
                        )
                );
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };

    }
}