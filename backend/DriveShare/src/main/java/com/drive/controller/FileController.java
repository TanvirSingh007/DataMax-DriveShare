package com.drive.controller;

import com.drive.dao.FileDao;
import com.drive.entity.File;
import com.drive.entity.Folder;
import com.drive.entity.Log;
import com.drive.response.FileUploadResponse;
import com.drive.service.*;
//import jdk.internal.loader.Resource;
import com.drive.util.FileInfo;
import com.drive.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private LogsService logsService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileManagerService fileManagerService;
    @Autowired
    private EncryptionManager encryptionManager;

    @PostMapping("/upload")
    public FileUploadResponse uploadDb(@RequestParam("file") MultipartFile multipartFile,
                                       @RequestParam("name") String name,
                                       @RequestParam("description") String description,
                                       @RequestParam("location") String folder,
                                       @RequestParam("userId") String userID,
                                       @RequestParam("share") String share)
    {
        File uploadedFile = fileUploadService.uploadToDb(multipartFile, name, description, folder, userID, share);
        FileUploadResponse response = new FileUploadResponse();
        if(uploadedFile!=null){
//            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/download/")
//                    .path(uploadedFile.getFileID())
//                    .toUriString();
//            response.setDownloadUri(downloadUri);
            response.setFileId(uploadedFile.getFileID());
            response.setFileType(uploadedFile.getFileType());
            response.setUploadStatus(true);
            response.setMessage("File Uploaded Successfully!");
            logsService.generateLog(new Log(uploadedFile.getFileName(), "Uploaded", userService.getUserFromID(userID).getName(), userID));

            return response;

        }
        response.setMessage("Oops! something went wrong please re-upload.");
        return response;
    }

    @GetMapping("/download/{fileID}/{userID}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileID, @PathVariable String userID)
    {
        try {
            File uploadedFileToRet = fileUploadService.downloadFile(fileID);
            logsService.generateLog(new Log(uploadedFileToRet.getFileName(), "Downloaded", userService.getUserFromID(userID).getName(), uploadedFileToRet.getUserID()));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + uploadedFileToRet.getFileName())
                    .body(new ByteArrayResource(encryptionManager.decryptData("Password", uploadedFileToRet.getFileData())));
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @PostMapping({"/deleteFile/{fileID}"})
    public String deleteFile(@PathVariable String fileID){
        return fileManagerService.deleteFile(fileID);
    }

    @GetMapping({"/userFiles/{userID}"})
    public List<FileInfo> getUserFiles(@PathVariable String userID){
        return fileManagerService.getUserFiles(userID);
    }

    @GetMapping("/sensitiveFiles")
    public List<FileInfo> getSensitiveFiles(){
        return fileManagerService.getSensitiveFiles();
    }

    @PostMapping("/updateFileShare/{fileId}/{share}")
    public String updateFileShare(@PathVariable String share, @PathVariable String fileId){
        return fileManagerService.updateFileShare(share, fileId);
    }

    @GetMapping({"/sharedFiles/{organization}"})
    public List<FileInfo> getSharedFiles(@PathVariable String organization){
        return fileManagerService.getSharedFiles(organization);
    }

    @PostMapping({"/createFolder/{userID}/{folderName}"})
    public String createFolder(@PathVariable String userID, @PathVariable String folderName){
        return fileManagerService.createFolder(userID, folderName);
    }

    @GetMapping({"/folders/{userID}"})
    public List<Folder> getFolderList(@PathVariable String userID){
        return fileManagerService.getFolderList(userID);
    }

}
