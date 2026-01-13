package com.example.game_doan_so.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private String email;
    private Integer score;
    private Integer turns;
}
