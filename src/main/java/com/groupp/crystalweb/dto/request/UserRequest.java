package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Role;

import java.util.List;


public record UserRequest (
    String userName,
    String password,
    String email,
    List<Role> role

){

}