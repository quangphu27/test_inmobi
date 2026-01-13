package com.example.game_doan_so.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyTurnsResponse {
    private String message;
    private Integer turns;
}
