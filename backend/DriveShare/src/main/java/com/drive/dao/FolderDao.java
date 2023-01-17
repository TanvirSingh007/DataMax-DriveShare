package com.drive.dao;

import com.drive.entity.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderDao extends CrudRepository<Folder, String> {
    @Query("from Folder e where e.userID = :userID")
    List<Folder> FindAllByUserID(String userID);
}
