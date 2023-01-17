package com.drive.dao;

import com.drive.entity.Settings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingsDao  extends CrudRepository<Settings, Integer> {
    @Override
    List<Settings> findAll();
}
