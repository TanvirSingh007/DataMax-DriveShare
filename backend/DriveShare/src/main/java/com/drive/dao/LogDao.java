package com.drive.dao;

import com.drive.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDao extends CrudRepository<Log, Long> {
    List<Log> findAllByFileUserID(String fileUserID);
}
