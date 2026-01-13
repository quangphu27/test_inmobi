package com.example.game_doan_so.service;

import com.example.game_doan_so.dto.GuessResponse;
import com.example.game_doan_so.entity.User;
import com.example.game_doan_so.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final double WIN_PROBABILITY = 0.05;
    private final Random random = new Random();

    @Transactional
    public GuessResponse guess(Long userId, Integer userGuess) {
        User user = userRepository.findByIdWithLock(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getTurns() <= 0) {
            throw new RuntimeException("No turns remaining");
        }

        user.setTurns(user.getTurns() - 1);

        boolean isWin = random.nextDouble() < WIN_PROBABILITY;
        int serverNumber = isWin ? userGuess : generateDifferentNumber(userGuess);

        if (isWin) {
            user.setScore(user.getScore() + 1);
        }

        userRepository.save(user);

        return new GuessResponse(
                isWin,
                serverNumber,
                userGuess,
                user.getScore(),
                user.getTurns(),
                isWin ? "Congratulations! You guessed correctly!" : "Sorry, try again!"
        );
    }

    private int generateDifferentNumber(int userGuess) {
        int number;
        do {
            number = random.nextInt(5) + 1;
        } while (number == userGuess);
        return number;
    }

    @Transactional
    public void buyTurns(Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!password.equals("phupass")) {
            throw new RuntimeException("Invalid password");
        }

        user.setTurns(user.getTurns() + 5);
        userRepository.save(user);
    }
}
