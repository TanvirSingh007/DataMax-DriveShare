package com.drive.service;

import com.drive.dao.OrganizationDao;
import com.drive.dao.SettingsDao;
import com.drive.entity.Organization;
import com.drive.entity.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {

    @Autowired
    SettingsDao settingsDao;

    @Autowired
    OrganizationDao organizationDao;

    public String getGuidelines(){
//        settingsDao.save(new Settings("testtststs"));
        return settingsDao.findAll().get(0).getGuidelines();
//        return "tetete";
    }

    public String setGuidelines(Settings settings) {
        try {
            settingsDao.deleteAll();
            settingsDao.save(settings);
            return "Guidelines updated!";
        }
        catch (Exception e){
            return "Error updating guidelines!";
        }
    }

    public String addOrganization(Organization organization) {
        try {
            organizationDao.save(organization);
            return "Organization added successfully!";
        }
        catch (Exception e){
            return "Error adding organization";
        }
    }

    public List<Organization> getOrgList(){
        return organizationDao.findAll();
    }
}
