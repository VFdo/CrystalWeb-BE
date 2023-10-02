package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.models.User;

//@Data
//@Builder
public record UserRequest (
    String userId,
    String userName,
    String password,
    Role role //?

){

}