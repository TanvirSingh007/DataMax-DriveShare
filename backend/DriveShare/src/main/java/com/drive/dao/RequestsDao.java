package com.drive.dao;

import com.drive.entity.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsDao extends CrudRepository<Request, Long> {
    List<Request> findAllFileByReceiverID(String receiverID);
//    void deleteAllByFileID(String fileID);
}
