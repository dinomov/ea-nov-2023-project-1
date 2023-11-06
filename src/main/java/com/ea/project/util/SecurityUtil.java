package com.ea.project.util;

import com.ea.project.entity.User;
import com.ea.project.respository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserRepo repository;

    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();
    }

    public User getCurrentUser() {
           return repository.findByEmail(getCurrentUserName())
                   .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
