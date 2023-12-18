package edu.CourseProject.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecureServiceImpl implements SecureService{
    @Override
    public boolean isUserInRole(String role) {
        Authentication authentication = getAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
