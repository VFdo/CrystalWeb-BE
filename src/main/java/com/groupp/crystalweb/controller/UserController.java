package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.UserRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.User;
import com.groupp.crystalweb.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



//    save user - moved to Auth Controller

//    get all users
    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser(@RequestParam(defaultValue = "0") int pageNumber,
                                               @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object , Object> allUsers = userService.getAllUsers(pageNumber,pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allUsers.first(),
                (PageInfo) allUsers.second()
        );

        return ResponseEntity.ok(response);
    }

//    find user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable String id){
        User existingUser = userService.getUser(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingUser
        );

        return ResponseEntity.ok(response);
    }

//    update user by id
    @PutMapping("user/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest){
        User updatedUser = userService.updateUser(id,userRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedUser
        );
        return ResponseEntity.ok(response);
    }

//    delete by id
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
        long deleted = userService.deleteUser(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted != 0){
            response.setPayload("User Deleted Successfully");
        }else{
            response.setPayload("User not found");
        }
        return ResponseEntity.ok(response);
    }

}
