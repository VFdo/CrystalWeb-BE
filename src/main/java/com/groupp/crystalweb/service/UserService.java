package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.UserRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.User;
import com.groupp.crystalweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;



    public User saveUser(UserRequest userRequest){
        log.info("User save request received");
        try {
            User newUser = new User();
            newUser.setUserName(userRequest.userName());
            newUser.setPassword(userRequest.password());
            newUser.setRole(userRequest.role());
            return userRepository.save(newUser);
        }catch (Exception e){
            log.info("User saving failed: {},{}",e.getMessage());
            throw new RuntimeException("Error");
        }

    }

    public Tuple<Object,Object> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable  = PageRequest.of(pageNumber,pageSize);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        PageInfo pageInfo = new PageInfo(
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages());
        return new Tuple<>(users,pageInfo);

    }

    public User getUser(String userId) {
        Optional<User> user = userRepository.findByRefId(userId); //?
        if(user.isPresent()) return user.get();
//        TODO: handle response
        return null;
    }

    public User updateUser(String userId, UserRequest userRequest) {
        Optional<User> user = userRepository.findByRefId(userId);
        if(user.isPresent()){
            User existingUser = user.get();
            existingUser.setUserName(userRequest.userName());
            existingUser.setPassword(userRequest.password());
            existingUser.setRole(userRequest.role());

            return userRepository.save(existingUser);
        }
        log.info("User not found for id: P{}",userId);
        return null;
    }

    public long deleteUser(String userId) {

        return userRepository.deleteByRefId(userId);
    }
}
