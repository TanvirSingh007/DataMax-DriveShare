package com.drive.controller;

import com.drive.entity.Organization;
import com.drive.entity.Settings;
import com.drive.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriveController {

    @Autowired
    SettingsService settingsService;
    @PostMapping({"/updateGuidelines"})
    public String  setGuidelines(@RequestBody Settings settings){
        return settingsService.setGuidelines(settings);
    }

    @GetMapping({"/guidelines"})
    public String getGuidelines(){
        return settingsService.getGuidelines();
    }

    @PostMapping({"/addOrg"})
    public String addOrganization(@RequestBody Organization organization){
        return settingsService.addOrganization(organization);
    }

    @GetMapping({"/getOrgList"})
    public List<Organization> getOrgList(){
        return settingsService.getOrgList();
    }
}
