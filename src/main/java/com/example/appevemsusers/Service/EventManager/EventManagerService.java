package com.example.appevemsusers.Service.EventManager;

import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.AbstractBaseService;
import com.example.appevecommon.Service.Exception.ResourceNotFoundException;
import com.example.appevecommon.Service.Utilities.Responses.Ok.PageResult;
import com.example.appevecommon.Service.Utilities.Responses.Response;
import com.example.appevemsusers.Client.UserClient;
import com.example.appevemsusers.Config.RedisCacheConfig;
import com.example.appevemsusers.Repository.IEventManagerRepository;
import com.example.appevemsusers.Service.EventManager.Exception.ManagerAlreadyAssignedException;
import com.example.appevemsusers.Service.EventManager.Exception.NotAnEventManagerException;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CreateEventManagerDto;
import org.openapitools.model.EventManagerDto;
import org.openapitools.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@Slf4j
public class EventManagerService extends AbstractBaseService<
        EventManager,
        EventManagerDto,
        EventManagerMapper,
        EventManagerFilter> {
    private final IEventManagerRepository eventManagerRepository;

    private final UserClient userClient;
    protected EventManagerService(IEventManagerRepository repository, EventManagerMapper mapper, UserClient userClient) {
        super(repository, mapper);
        this.eventManagerRepository=repository;
        this.userClient = userClient;
    }
    @Autowired
    private ObjectMapper objectMapper;

    @CachePut(value = RedisCacheConfig.EVENT_MANAGER_SECTION, key="'api_event_manager_' + #result.id")
    public EventManagerDto createEventManager(CreateEventManagerDto dto){

        Response<Object> response = userClient.getById(dto.getManagerId());
        log.debug(" SE PIDIO EL MANAGER CON ID {}", dto.getManagerId()  );
        log.debug(" RESPONSE: {}",response.toString());
        if (!response.success() || response.data() == null) {
            throw new ResourceNotFoundException("No se pudo obtener el usuario consultado");
        }
        UserDto user = objectMapper.convertValue(response.data(), UserDto.class);
        if(! ("Manager".equals(user.getRoleName()))) {
            throw new NotAnEventManagerException(user);
        }
        if(eventManagerRepository.findByManagerIdAndEventId(dto.getManagerId(), dto.getEventId()).isPresent()){
            throw new ManagerAlreadyAssignedException(user);
        };
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    @Cacheable(value= RedisCacheConfig.EVENT_MANAGER_SECTION,key = "'api_event_manager_' + #id")
    public EventManagerDto getById(Long id){
        return super.getById(id);
    }

    public PageResult<EventManagerDto> getByManagerId(Long managerId, EventManagerFilter filter){
        Pageable pageable = (filter != null) ? filter.toPageable() : PageRequest.of(0, AbstractBaseService.DEFAULT_SIZE);
        return PageResult.from(eventManagerRepository.findByManagerId(managerId,pageable).map(mapper::toDto));
    }
    public PageResult<EventManagerDto> getByEventId(Long eventId, EventManagerFilter filter){
        Pageable pageable = (filter != null) ? filter.toPageable() : PageRequest.of(0, AbstractBaseService.DEFAULT_SIZE);
        return PageResult.from(eventManagerRepository.findByEventId(eventId,pageable).map(mapper::toDto));
    }

    @Override
    @CacheEvict(value = RedisCacheConfig.EVENT_MANAGER_SECTION, key ="'api_event_manager_' + #id" )
    public boolean delete(Long id){
        return super.delete(id);
    }

    @Override
    public Specification<EventManager> toSpecification(EventManagerFilter filter) {
        return null;
    }
}
