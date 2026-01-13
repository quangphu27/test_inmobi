package com.example.game_doan_so.controller;

import com.example.game_doan_so.dto.BuyTurnsRequest;
import com.example.game_doan_so.dto.BuyTurnsResponse;
import com.example.game_doan_so.dto.GuessRequest;
import com.example.game_doan_so.dto.GuessResponse;
import com.example.game_doan_so.entity.User;
import com.example.game_doan_so.repository.UserRepository;
import com.example.game_doan_so.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/guess")
    public ResponseEntity<GuessResponse> guess(@Valid @RequestBody GuessRequest request, Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            GuessResponse response = gameService.guess(user.getId(), request.getNumber());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GuessResponse(false, 0, request.getNumber(), 0, 0, e.getMessage()));
        }
    }

    @PostMapping("/buy-turns")
    public ResponseEntity<BuyTurnsResponse> buyTurns(@RequestBody BuyTurnsRequest request, Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            gameService.buyTurns(user.getId(), request.getPassword());
            User updatedUser = userRepository.findById(user.getId()).orElse(user);
            return ResponseEntity.ok(new BuyTurnsResponse("Thanh toán thành công", updatedUser.getTurns()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BuyTurnsResponse("Thanh toán không thành công('phupass' mới được)", 0));
        }
    }
}
