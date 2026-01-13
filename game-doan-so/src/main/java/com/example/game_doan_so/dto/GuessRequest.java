package com.example.game_doan_so.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class GuessRequest {
    @Min(1)
    @Max(5)
    private Integer number;
}
