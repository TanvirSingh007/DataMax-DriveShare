package com.drive.service;

import com.drive.dao.FileDao;
import com.drive.dao.FolderDao;
import com.drive.dao.UserDao;
import com.drive.entity.File;
import com.drive.entity.Folder;
import com.drive.util.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileManagerService {

    @Autowired
    FileDao fileDao;
    
    @Autowired
    FolderDao folderDao;

    @Autowired
    UserDao userDao;

    public List<FileInfo> getUserFiles(String userID){
        List<File> files = fileDao.findAllFileByUserID(userID);
        List<FileInfo> fileList = new ArrayList<>();
        for(int i = 0; i<files.size();i++){
            fileList.add(new FileInfo(files.get(i).getFileID(),
                    userDao.findUserByEmail(files.get(i).getUserID()).get().getName(),
                    files.get(i).getShare(),
                    files.get(i).getDescription(),
                    files.get(i).getFileName(),
                    files.get(i).getLocation(),
                    files.get(i).getUserID()));
        }
        return fileList;
    }

    public List<FileInfo> getSharedFiles(String organization){
        List<File> files = fileDao.findAllFileByOrganization(organization);
        List<FileInfo> fileList = new ArrayList<>();
        for(int i = 0; i<files.size();i++){
            if(files.get(i).getShare().equals("yes"))
                fileList.add(new FileInfo(files.get(i).getFileID(),
                        userDao.findUserByEmail(files.get(i).getUserID()).get().getName(),
                        files.get(i).getShare(),
                        files.get(i).getDescription(),
                        files.get(i).getFileName(),
                        files.get(i).getLocation(),
                        files.get(i).getUserID()));
        }
        return fileList;
    }

    public List<FileInfo> getSensitiveFiles(){
        List<File> files =  fileDao.findAllFileByShare("no");
        List<FileInfo> fileList = new ArrayList<>();
        for(int i = 0; i<files.size();i++){
            fileList.add(new FileInfo(files.get(i).getFileID(),
                    userDao.findUserByEmail(files.get(i).getUserID()).get().getName(),
                    files.get(i).getShare(),
                    files.get(i).getDescription(),
                    files.get(i).getFileName(),
                    files.get(i).getLocation(),
                    files.get(i).getUserID()));
        }
        return fileList;
    }

    public String deleteFile(String fileID){
        try{
            fileDao.deleteById(fileID);
            return "File deleted successfully";
        }
        catch (Exception e){
            return "Error deleting file";
        }
    }

    public String createFolder(String userID, String folderName){
        try{
            folderDao.save(new Folder(userID, folderName));
            return "success";
        }
        catch (Exception e){
            System.out.println(e);
            return "error";
        }
    }

    public String updateFileShare(String share, String fileID){
        try{
            fileDao.setShareStatus(share, fileID);
            return "Updated Successfully!";
        }
        catch (Exception e){
            return "Error updating";
        }
    }

    public List<Folder> getFolderList(String userID){
        return folderDao.FindAllByUserID(userID);
    }

}

