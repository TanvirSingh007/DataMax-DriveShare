package com.drive.controller;

import com.drive.entity.Request;
import com.drive.service.FileRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestsController {

    @Autowired
    FileRequestService fileRequestService;

    @PostMapping({"/requestFile"})
    public String requestFile(@RequestBody Request request){
        return fileRequestService.newRequest(request);
    }

    @PostMapping({"/acceptRequest/{requestId}"})
    public String acceptRequest(@PathVariable String requestId){
        return fileRequestService.acceptRequest(Long.parseLong(requestId));
    }

    @PostMapping({"/denyRequest/{requestId}"})
    public String denyRequest(@PathVariable String requestId){
        return fileRequestService.denyRequest(Long.parseLong(requestId));
    }

    @GetMapping({"/requests/{userId}"})
    public List<Request> getRequests(@PathVariable String userId){
        return fileRequestService.requestList(userId);
    }

}
