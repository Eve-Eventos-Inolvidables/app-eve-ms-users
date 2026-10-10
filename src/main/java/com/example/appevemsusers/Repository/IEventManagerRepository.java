package com.example.appevemsusers.Repository;

import com.example.appevecommon.Models.User.EventManager;
import com.example.appevecommon.Repository.IBaseRepository;
import org.hibernate.internal.util.Optional;

import java.util.List;

public interface IEventManagerRepository extends IBaseRepository<EventManager> {
    List<EventManager> findByManagerId(Long managerId);
}
