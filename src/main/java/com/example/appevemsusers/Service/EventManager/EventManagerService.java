package com.example.appevemsusers.Service.EventManager;

import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.AbstractBaseService;
import com.example.appevecommon.Service.Exception.ResourceNotFoundException;
import com.example.appevemsusers.Config.RedisCacheConfig;
import com.example.appevemsusers.Repository.IEventManagerRepository;
import com.example.appevemsusers.Service.EventManager.Exception.NotAnEventManagerException;
import org.openapitools.model.CreateEventManagerDto;
import org.openapitools.model.EventManagerDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EventManagerService extends AbstractBaseService<
        EventManager,
        EventManagerDto,
        EventManagerMapper,
        EventManagerFilter> {

    protected EventManagerService(IEventManagerRepository repository, EventManagerMapper mapper) {
        super(repository, mapper);
//        this.userRepository=userRepository;
    }

    @CachePut(value = RedisCacheConfig.EVENT_MANAGER_SECTION, key="'api_event_manager_' + #result.id")
    public EventManagerDto createEventManager(CreateEventManagerDto dto){

//        User manager = userRepository.findById(dto.getManagerId())
//                .orElseThrow(()->new ResourceNotFoundException(dto.getEventId()));
//
//        if(!"Manager".equals(manager.getRole().getName() ) ) {
//            throw new NotAnEventManagerException(manager);
//        }
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    @Cacheable(value= RedisCacheConfig.EVENT_MANAGER_SECTION,key = "'api_event_manager_' + #id")
    public EventManagerDto getById(Long id){
        return super.getById(id);
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
