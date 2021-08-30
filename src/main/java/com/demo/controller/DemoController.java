package com.demo.controller;

import com.demo.entity.DataEntity;
import com.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mandi")
public class DemoController {

    @Autowired
    DemoService demoService;

    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public String createMandiBhav(@RequestBody DataEntity dataEntity){
        System.out.println(dataEntity);
        demoService.makeRequest(dataEntity);
        return "json reached."+ dataEntity.toString();
    }
    @PostMapping("/add/{collection}")
    @CrossOrigin(origins = "*")
    public String createRandomData(@PathVariable String collection ){
        System.out.println(collection);
        demoService.addToGivenCollection(collection);
        return "json reached."+ collection;
    }

    @GetMapping("/get/{collection}")
    @CrossOrigin(origins = "*")
    public long getCount(@PathVariable String collection){
        return demoService.get(collection);
    }
}
