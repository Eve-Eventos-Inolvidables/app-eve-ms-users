package com.example.appevemsusers.Repository;

import com.example.appevecommon.Models.User.User;
import com.example.appevecommon.Repository.IArchivableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IArchivableRepository<User> {

}