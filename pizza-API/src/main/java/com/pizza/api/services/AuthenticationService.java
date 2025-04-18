package com.pizza.api.services;

import com.pizza.core.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No hay un usuario autenticado");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            return (User) principal;
        } else if (principal instanceof UserDetails) {
            return mapUserDetailsToUser((UserDetails) principal);
        } else {
            throw new IllegalStateException("El principal no es una instancia válida de User o UserDetails");
        }
    }

    private User mapUserDetailsToUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        // Agrega más propiedades si es necesario
        return user;
    }
}