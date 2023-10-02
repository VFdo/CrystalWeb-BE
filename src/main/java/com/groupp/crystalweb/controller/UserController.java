package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.UserRequest;
import com.groupp.crystalweb.models.User;
import com.groupp.crystalweb.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



//    save user
    @PostMapping("/user")
    public User saveUser(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

//    get all users
    @GetMapping("/user")
    public List<User> getUser(){
        return userService.getAllUsers();

    }

//    get by id
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUser(id);
    }

//    update by id
    @PutMapping("demo/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserRequest userRequest){
        return userService.update(id, userRequest); //?
    }

//    delete by id
    @DeleteMapping("user/delete/{id}")
    public String deleteUser(@PathVariable String id){
        long deleted = userService.deleteUser(id);
        if(deleted != 0){
            return ("Item deleted successfully");
        }
        return "Item not found!";
    }

}
