package com.drive.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Settings {

    @Id
    private int Id = 1;
    private String guidelines;

    public String getGuidelines() {
        return guidelines;
    }

    public Settings(String guidelines) {
        this.guidelines = guidelines;
    }

    public Settings() {
    }

    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
    }

}
