package com.drive.service;

import com.drive.dao.*;
import com.drive.entity.Organization;
import com.drive.entity.Role;
import com.drive.entity.Settings;
import com.drive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SettingsDao settingsDao;

    public void initRoles(){
//        Role adminRole = new Role();
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescription("Admin role");
//        roleDao.save(adminRole);

//        Role userRole = new Role();
//        userRole.setRoleName("New");
//        userRole.setRoleDescription("Default role for newly created record");
//        roleDao.save(userRole);

        Role newUserRole = new Role();
        newUserRole.setRoleName("User");
        newUserRole.setRoleDescription("Accepted User Role");
        roleDao.save(newUserRole);

        Role blockedUserRole = new Role();
        blockedUserRole.setRoleName("Blocked");
        blockedUserRole.setRoleDescription("Blocked User Role");
        roleDao.save(blockedUserRole);

        Organization one = new Organization();
        one.setName("One");
        organizationDao.save(one);

        Organization two = new Organization();
        two.setName("Two");
        organizationDao.save(two);

        Organization three = new Organization();
        three.setName("Three");
        organizationDao.save(three);

        Organization four = new Organization();
        four.setName("Four");
        organizationDao.save(four);

        Organization five = new Organization();
        five.setName("Five");
        organizationDao.save(five);

    }

    public void initRoleAndUser() {

        Settings settings = new Settings("Test Guidelines");
        settingsDao.save(settings);

        User adminUser = new User();
        adminUser.setEmail("admin123");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        adminUser.setOrganization("adminORG");
        adminUser.setName("adminUser");
        Role role = new Role();
        role.setRoleName("Admin");
        role.setRoleDescription("Admin Role");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(role);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
    }

    public User registerNewUser(User user) {
        Role userRole = new Role();
        userRole.setRoleName("New");
        userRole.setRoleDescription("Default role for newly created record");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userDao.save(user);
    }

    public String updateRole(String userID, String newRole){
        try {
//            Role role = roleDao.findById(newRole).get();
//            Set<Role> userRoles = new HashSet<>();
//            userRoles.add(role);
//            userDao.updateRole(userID, userRoles);
        }
        catch (Exception e){
            System.out.println(e);
            return "Error while updating role!";
        }
        return "Role Updated Successfully!";
    }

    public User getUserFromID(String userID){
        return userDao.findUserByEmail(userID).get();
    }

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public  List<User> getOrgUsers(String organization){
        return userDao.findAllByOrganization(organization);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
