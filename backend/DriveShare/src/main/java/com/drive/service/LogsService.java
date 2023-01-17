package com.drive.service;

import com.drive.dao.LogDao;
import com.drive.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LogsService {

    @Autowired
    LogDao logDao;

    public List<Log> getLogs(String userID){
        return logDao.findAllByFileUserID(userID);
    }

    public String generateLog(Log log){
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            log.setTime(timestamp);
            logDao.save(log);
            return "Log created successfully!";
        }
        catch (Exception e){
            System.out.println(e);
            return "Error creating log";
        }
    }

}
