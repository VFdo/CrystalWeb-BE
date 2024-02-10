package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.LoginRequest;
import com.groupp.crystalweb.dto.request.UserRequest;
import com.groupp.crystalweb.dto.response.JwtResponse;
import com.groupp.crystalweb.entity.Role;
import com.groupp.crystalweb.entity.User;
import com.groupp.crystalweb.entity.UserDetailsImpl;
import com.groupp.crystalweb.repository.UserRepository;
import com.groupp.crystalweb.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@Slf4j
//@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        log.info("login request received...");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest signUpRequest) {
        log.info("user registration request received!");
        if (userRepository.findByUsername(signUpRequest.userName()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.findByEmail(signUpRequest.email()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        User user = new User(signUpRequest.userName(),
                signUpRequest.email(),
                encoder.encode(signUpRequest.password()));

        List<Role> strRoles = signUpRequest.role();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            roles.add(Role.CLIENT);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case ADMIN:
                        log.info("setting admin role");
                        roles.add(Role.ADMIN);
                        break;
                    case STAFF:
                        log.info("setting staff role");
                        roles.add(Role.STAFF);
                        break;
                    case CLIENT:
                        log.info("setting client role");
                        roles.add(Role.CLIENT);
                        break;
                    default:
                        log.info("invalid role mapping!");
                        throw new RuntimeException("Invalid role mapping attempted!");
                }
            });
        }
        user.setRole(roles);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
