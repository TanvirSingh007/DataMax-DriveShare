package com.drive.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Log {


    private String fileID;
    private String action;
    private String userName;

    private String fileUserID;
    @Id
    private Timestamp time;

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public Log() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFileUserID() {
        return fileUserID;
    }

    public void setFileUserID(String fileUserID) {
        this.fileUserID = fileUserID;
    }

    public Log(String fileID, String action, String userName, String fileUserID) {
        this.fileID = fileID;
        this.action = action;
        this.userName = userName;
        this.fileUserID = fileUserID;
    }
}
