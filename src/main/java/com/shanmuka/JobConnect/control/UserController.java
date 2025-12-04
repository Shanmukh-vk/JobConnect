package com.shanmuka.JobConnect.control;

import com.shanmuka.JobConnect.dto.*;
import com.shanmuka.JobConnect.model.User;
import com.shanmuka.JobConnect.repo.UserRepository;
import com.shanmuka.JobConnect.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins ="http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

        // 1. Check if email already exists
        if (userRepo.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists");
        }

        // 2. Create & save user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // later you can encode
        user.setRole(request.getRole() != null ? request.getRole() : "JOB_SEEKER");

        User savedUser = userRepo.save(user);

        // 3. Optionally generate token immediately after signup
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole(), savedUser.getUserId());

        return ResponseEntity.ok(
                new LoginResponse(token, savedUser.getUserId(), savedUser.getRole())
        );
    }


        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

            User user = userRepo.findByEmail(loginRequest.getEmail());

            // check null FIRST to avoid 500 error
            if (user == null) {
                return ResponseEntity.badRequest().body("Email not registered");
            }

            if (!user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getUserId());

            return ResponseEntity.ok(
                    new LoginResponse(token, user.getUserId(), user.getRole())
            );
        }
    }