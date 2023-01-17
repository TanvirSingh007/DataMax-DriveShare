package com.drive.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Folder {


    private String userID;
    @Id
    private String name;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public Folder() {
    }
}
