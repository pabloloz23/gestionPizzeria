package com.pizza.api.controllers;

import com.pizza.core.exception.UserNotFoundException;
import com.pizza.core.model.User;
import com.pizza.core.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public User getUserProfile(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PatchMapping("/{userId}/password")
    public void updatePassword(
            @PathVariable Long userId,
            @RequestBody String newPassword
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}