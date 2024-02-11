package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Role;


public record UserRequest (
    String userName,
    String password,
    Role role

){

}