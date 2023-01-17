package com.drive.service.serviceImpl;

import com.drive.dao.FileDao;
import com.drive.dao.UserDao;
import com.drive.entity.File;
import com.drive.service.EncryptionManager;
import com.drive.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileDao fileUploadRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EncryptionManager encryptionManager;

    @Override
    public File uploadToDb(MultipartFile file, String name, String description, String folder, String userID, String share) {

        File uploadedFile = new File();
        try {
            uploadedFile.setFileData(encryptionManager.encryptData("Password", file.getBytes()));
            uploadedFile.setFileType(file.getContentType());
            uploadedFile.setFileName(name);
            uploadedFile.setShare(share);
            uploadedFile.setDescription(description);
            uploadedFile.setLocation(folder);
            uploadedFile.setUserID(userID);
            uploadedFile.setOrganization(userDao.findById(userID).get().getOrganization());

            File uploadedFileToRet = fileUploadRepository.save(uploadedFile);
            return uploadedFileToRet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public File downloadFile(String fileId) {
        File uploadedFileToRet = fileUploadRepository.getOne(fileId);
        return uploadedFileToRet;
    }
}
