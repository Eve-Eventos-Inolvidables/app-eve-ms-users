package com.example.appevemsusers.Service.EventManager;

import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Service.AbstractBaseService;
import com.example.appevecommon.Service.Exception.ResourceNotFoundException;
import com.example.appevemsusers.Repository.IEventManagerRepository;
import com.example.appevemsusers.Repository.IUserRepository;
import com.example.appevemsusers.Service.EventManager.Exception.NotAnEventManagerException;
import org.openapitools.model.CreateEventManagerDto;
import org.openapitools.model.EventManagerDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EventManagerService extends AbstractBaseService<
        EventManager,
        EventManagerDto,
        EventManagerMapper,
        EventManagerFilter> {

    private final IUserRepository userRepository;
    protected EventManagerService(IEventManagerRepository repository, EventManagerMapper mapper, IUserRepository userRepository) {
        super(repository, mapper);
        this.userRepository=userRepository;
    }

    public EventManagerDto createEventManager(CreateEventManagerDto dto){

        User manager = userRepository.findById(dto.getManagerId())
                .orElseThrow(()->new ResourceNotFoundException(dto.getEventId()));

        if(!"Manager".equals(manager.getRole().getName() ) ) {
            throw new NotAnEventManagerException(manager);
        }

        ///CHECK FOR EVENT_ID IN EVENTS MS


        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Specification<EventManager> toSpecification(EventManagerFilter filter) {
        return null;
    }
}
