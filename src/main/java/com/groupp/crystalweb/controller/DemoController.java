package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.DemoRequest;
import com.groupp.crystalweb.entity.Demo;
import com.groupp.crystalweb.service.DemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

//    test controller
    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }

//    save
    @PostMapping("/demo")
    public Demo saveDemo(@RequestBody DemoRequest demoRequest){
        Demo demo = demoService.saveDemo(demoRequest);
        return (demo);
    }

//    get all
    @GetMapping("/demo")
    public List<Demo> getDemos(){
        List<Demo> demos = demoService.getAllDemos();
        return demos;
    }

//    get by id
    @GetMapping("/demo/{id}")
    public Demo getDemo(@PathVariable String id){
        Demo demo = demoService.getDemo(id);
        return demo;
    }

//    update by id
    @PutMapping("demo/{id}")
    public Demo updateDemo(@PathVariable String id, @RequestBody DemoRequest demoRequest){
        Demo demo = demoService.update(id, demoRequest);
        return demo;
    }

//    delete by id
    @DeleteMapping("demo/{id}")
    public String deleteDemo(@PathVariable String id){
        long deleted = demoService.deleteDemo(id);
        if(deleted != 0){
            return ("Item deleted successfully");
        }
        return "Item not found!";
    }

}
