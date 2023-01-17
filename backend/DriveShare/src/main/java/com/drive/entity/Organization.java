package com.drive.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Organization {

    @Id
    private String name;

    public Organization(String name) {
        this.name = name;
    }

    public Organization() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
