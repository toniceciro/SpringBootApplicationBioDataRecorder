package com.toniceciro.springbootaccessapplication.controller;
import com.toniceciro.springbootaccessapplication.model.requestBody;
import com.toniceciro.springbootaccessapplication.model.responseBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.toniceciro.springbootaccessapplication.createEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/ketto")
public class AccessController {
    @Autowired
    protected createEntryService createEntryService;
    //For creating new entries to database
    @PostMapping(path = "/data/create", produces = {"application/json"})
    public responseBody createEntry(@RequestBody requestBody data){
        responseBody response = new responseBody();
        response = createEntryService.createNewEntry(data);
        return response;
    }
}
