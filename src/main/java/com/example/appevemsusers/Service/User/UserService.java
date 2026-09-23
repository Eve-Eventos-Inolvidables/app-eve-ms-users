package com.example.appevemsusers.Service.User;

import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.AbstractArchivableBaseService;
import com.example.appevemsusers.Repository.IUserRepository;
import com.example.appevemsusers.Service.User.Role.RoleService;
import jakarta.persistence.criteria.Predicate;
import org.openapitools.model.UserDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends AbstractArchivableBaseService<User, UserDto, UserFilter> {

    private final RoleService roleService;
    public UserService(IUserRepository repository, RoleService roleService) {
        super(repository);
        this.roleService=roleService;
    }

    @Override public UserDto toDto(User e) {
        if( e == null) return null;

        UserDto userDto = new UserDto();
        userDto.setId(e.getId());
        userDto.setName(e.getName());
        userDto.setEmail(e.getEmail());
        userDto.setArchived(e.isArchived());
        userDto.setRoleId(e.getRole().getId());

        return userDto;
    }
    @Override public User toEntity(UserDto d) {
        if(d == null) return null;
        User user = new User();
        user.setId(d.getId());
        user.setName(d.getName());
        user.setEmail(d.getEmail());
        user.setRole(roleService.getEntity(d.getRoleId()));
        user.setArchived(d.getArchived());
        return user;

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