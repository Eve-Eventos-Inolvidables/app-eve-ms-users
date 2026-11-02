package com.example.appevemsusers.Repository;

import com.example.appevecommon.Models.Event.SimpleEvent;
import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Repository.IBaseRepository;
import org.hibernate.internal.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEventManagerRepository extends IBaseRepository<EventManager> {
    Page<EventManager> findByManagerId(Long managerId, Pageable pageable);
    Page<EventManager> findByEventId(Long eventId, Pageable pageable);
}
