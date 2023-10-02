package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.UserRequest;
import com.groupp.crystalweb.models.User;
import com.groupp.crystalweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequest userRequest){
      User newUser = new User(
              userRequest.userId(),
              userRequest.userName(),
              userRequest.password(),
              userRequest.role()
        );
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {

        return (List<User>) userRepository.findAll();
    }

    public User getUser(String userId) {
        Optional<User> user = userRepository.findByUserId(userId); //?
        if(user.isPresent()){
            return user.get();
        }
//        TODO: handle response
        return null;
    }

    public User update(String userId, UserRequest userRequest) {
        Optional<User> user = userRepository.findByUserId(userId);
        if(user.isPresent()){
            User existingUser = user.get();
            existingUser.setUserId(userRequest.userId());
            existingUser.setUserName(userRequest.userName());
            existingUser.setPassword(userRequest.password());
            existingUser.setRole(userRequest.role());

            return userRepository.save(existingUser);
        }
        log.info("User not found for id: P{}", userRequest.userId());
        return null;
    }

    public long deleteUser(String userId) {

        return userRepository.deleteByUserId(userId);
    }
}
