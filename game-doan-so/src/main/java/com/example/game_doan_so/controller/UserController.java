package com.example.game_doan_so.controller;

import com.example.game_doan_so.dto.LeaderboardResponse;
import com.example.game_doan_so.dto.UserInfoResponse;
import com.example.game_doan_so.entity.User;
import com.example.game_doan_so.repository.UserRepository;
import com.example.game_doan_so.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getMe(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserInfoResponse response = userService.getUserInfo(user.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<LeaderboardResponse> getLeaderboard() {
        LeaderboardResponse response = userService.getLeaderboard();
        return ResponseEntity.ok(response);
    }
}
