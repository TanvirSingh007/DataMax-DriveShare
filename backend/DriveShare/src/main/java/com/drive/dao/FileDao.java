package com.drive.dao;

import com.drive.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FileDao extends JpaRepository<File,String> {

    List<File> findAllFileByUserID(String email);
    List<File> findAllFileByOrganization(String organization);
    List<File> findAllFileByShare(String share);
    @Transactional
    @Modifying
    @Query("update File fil set fil.share = :share where fil.fileID = :fileID")
    void setShareStatus(@Param("share") String share, @Param("fileID") String fileID);

}
