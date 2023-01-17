package com.drive.dao;

import com.drive.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleDao extends CrudRepository<Role, String> {
    @Override
    List<Role> findAll();

}
