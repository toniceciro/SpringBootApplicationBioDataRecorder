package com.toniceciro.springbootaccessapplication.controller;
import com.toniceciro.springbootaccessapplication.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.toniceciro.springbootaccessapplication.createEntryService;
import com.toniceciro.springbootaccessapplication.getEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/ketto")
public class AccessController {
    @Autowired
    protected createEntryService createEntryService;
    @Autowired
    protected getEntryService getEntryService;
    //For creating new entries to database
    @PostMapping(path = "/data/create", produces = {"application/json"})
    public responseBody createEntry(@RequestBody requestBody data){
        responseBody response = new responseBody();
        response = createEntryService.createNewEntry(data);
        return response;
    }
    @GetMapping(path = "/data/viewAll", produces = {"application/json"})
    public responseEntryBody getAllEntries(){
        responseEntryBody response;
        response = getEntryService.getAllEntries();
        return response;
    }
    @GetMapping(path = "/data/view", produces = {"application/json"})
    public responseEntryBody getEntry(@RequestBody requestBody data){
        responseEntryBody response;
        response = getEntryService.getEntry(data);
        return response;
    }
}
