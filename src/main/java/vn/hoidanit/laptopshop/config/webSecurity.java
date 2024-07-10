package vn.hoidanit.laptopshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.service.CustomUserDetailsService;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class webSecurity {
    @Autowired
    private UserService userService;

    public boolean checkUserId(Authentication authentication, int id) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        String email = authentication.getName();

        vn.hoidanit.laptopshop.domain.User user = this.userService.getUserByEmail(email);

        if (user.getId() == id) {
            return true;
        } else {
            return false;
        }
    }
}
