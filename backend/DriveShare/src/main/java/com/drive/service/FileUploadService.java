package com.drive.service;

import com.drive.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    public File uploadToDb(MultipartFile file, String  name, String description, String folder, String userID, String share );
    public File downloadFile(String fileId);
}
