package com.drive.service;

import com.drive.dao.FileDao;
import com.drive.dao.RequestsDao;
import com.drive.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileRequestService {

    @Autowired
    RequestsDao requestsDao;
    @Autowired
    FileDao fileDao;

    public String newRequest(Request request){
        try {
            requestsDao.save(request);
            return "File Requested Successfully!";
        }
        catch (Exception e){
            return "Error while creating request!";
        }

    }

    public String acceptRequest(Long requestId){
        try {
            Optional<Request> request = requestsDao.findById(requestId);
            fileDao.setShareStatus("yes", request.get().getFileID());
            requestsDao.deleteById(requestId);
            return "Request Accepted!";
        }
        catch (Exception e){
            System.out.println(e);
            return "Error while accepting request!";
        }
    }

    public String denyRequest(Long requestId){
        try {
            Optional<Request> request = requestsDao.findById(requestId);
            requestsDao.deleteById(requestId);
            return "Request Denied!";
        }
        catch (Exception e){
            return "Error while denying request!";
        }
    }

    public List<Request> requestList(String userId){
        return requestsDao.findAllFileByReceiverID(userId);
    }
}
