package com.drive.controller;

import com.drive.entity.Log;
import com.drive.entity.User;
import com.drive.service.LogsService;
import com.drive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogsService logsService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoles();
        userService.initRoleAndUser();
    }


    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping({"/updateRole/{userId}/{role}"})
    public String updateRole(@PathVariable String userId, @PathVariable String role){
        return userService.updateRole(userId, role);
    }

    @GetMapping({"/userList"})
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @GetMapping({"/userList/{organization}"})
    public List<User> getOrgUserList(@PathVariable String organization){
        return userService.getOrgUsers(organization);
    }

    @PostMapping({"/createLog"})
    public String createLog(@RequestBody Log log){
        return logsService.generateLog(log);
    }

    @GetMapping({"/getLogs/{userID}"})
    public List<Log> getLogs(@PathVariable String userID){
        return logsService.getLogs(userID);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
