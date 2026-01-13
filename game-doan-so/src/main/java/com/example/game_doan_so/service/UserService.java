package com.example.game_doan_so.service;

import com.example.game_doan_so.dto.LeaderboardEntry;
import com.example.game_doan_so.dto.LeaderboardResponse;
import com.example.game_doan_so.dto.UserInfoResponse;
import com.example.game_doan_so.entity.User;
import com.example.game_doan_so.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserInfoResponse(user.getEmail(), user.getScore(), user.getTurns());
    }

    @Cacheable(value = "leaderboard", unless = "#result == null")
    public LeaderboardResponse getLeaderboard() {
        List<User> topUsers = userRepository.findTop10ByOrderByScoreDesc();
        List<LeaderboardEntry> entries = topUsers.stream()
                .limit(10)
                .map(user -> new LeaderboardEntry(user.getUsername(), user.getScore()))
                .collect(Collectors.toList());

        return new LeaderboardResponse(entries);
    }
}
