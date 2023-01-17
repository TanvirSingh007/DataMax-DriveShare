package com.drive.service;

import com.drive.dao.RoleDao;
import com.drive.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }

    public List<Role> getRoles(){
        return roleDao.findAll();
    }
}
