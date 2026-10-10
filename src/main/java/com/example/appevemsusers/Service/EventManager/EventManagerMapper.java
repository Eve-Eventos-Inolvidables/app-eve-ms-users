package com.example.appevemsusers.Service.EventManager;
import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Service.Utilities.ReadableMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.CreateEventManagerDto;
import org.openapitools.model.EventManagerDto;

@Mapper(componentModel = "spring" )
public interface EventManagerMapper extends ReadableMapper<EventManager,EventManagerDto> {

    @Mapping( source = "manager.id",target = "managerId")
    @Mapping(source = "event.id",target = "eventId")
    EventManagerDto toDto(EventManager entity);

    @Mapping(source = "managerId", target = "manager.id")
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(target = "assignedAt", expression = "java(java.time.LocalDate.now())")
    EventManager toEntity(CreateEventManagerDto dto);
}
