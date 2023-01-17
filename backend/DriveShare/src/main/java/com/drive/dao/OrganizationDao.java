package com.drive.dao;

import com.drive.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationDao  extends CrudRepository<Organization, String> {
    @Override
    List<Organization> findAll();
}
