package com.example.appevemsusers.Repository;

import com.example.appevecommon.Models.User.Role;
import com.example.appevecommon.Repository.IArchivableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends IArchivableRepository<Role> {
}
