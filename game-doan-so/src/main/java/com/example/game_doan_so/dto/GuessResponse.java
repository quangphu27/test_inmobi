package com.example.game_doan_so.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuessResponse {
    private Boolean correct;
    private Integer serverNumber;
    private Integer userGuess;
    private Integer score;
    private Integer remainingTurns;
    private String message;
}
