package com.example.appevemsusers.Service.User;

import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.Exception.ResourceNotFoundException;
import com.example.appevecommon.Service.UpdatableService;
import com.example.appevemsusers.Repository.IEventManagerRepository;
import com.example.appevemsusers.Repository.IRoleRepository;
import com.example.appevemsusers.Repository.IUserRepository;
import com.example.appevemsusers.Service.EventManager.EventManagerService;
import jakarta.persistence.criteria.Predicate;
import org.openapitools.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends UpdatableService<User, UserDto, UpdateUserDto,UserMapper, UserFilter > {

    private final IRoleRepository roleRepository;
    private final IEventManagerRepository eventManagerRepository;
    private final EventManagerService eventManagerService;
    public UserService(
            IUserRepository repository ,
            UserMapper userMapper,
            IRoleRepository roleRepository,
            IEventManagerRepository eventManagerRepository,
            EventManagerService eventManagerService) {
        super(repository,userMapper);
        this.roleRepository=roleRepository;
        this.eventManagerRepository = eventManagerRepository;
        this.eventManagerService = eventManagerService;
    }

    public UserDto createUser(CreateUserDto dto){
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("El rol ingresado no existe"));
        User user = mapper.toEntity(dto);
        user.setRole(role);
        User savedUser = repository.save(user);
        return mapper.toDto(savedUser);
    }
    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario con id: " + id + " no existe");
        }

        List<EventManager> events = eventManagerRepository.findByManagerId(id);
        events.forEach(e -> eventManagerService.delete(e.getId()));

        return super.delete(id);
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