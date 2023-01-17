package com.drive.util;

public class FileInfo {
    private String fileID;
    private String userName;
    private String share;
    private String description;
    private String fileName;
    private String location;
    private String userID;

    public FileInfo(String fileID, String userName, String share, String description, String fileName, String location, String userID) {
        this.fileID = fileID;
        this.userName = userName;
        this.share = share;
        this.userID = userID;
        this.description = description;
        this.fileName = fileName;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
