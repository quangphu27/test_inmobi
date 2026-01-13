package com.example.game_doan_so.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LeaderboardResponse {
    private List<LeaderboardEntry> topUsers;
}
