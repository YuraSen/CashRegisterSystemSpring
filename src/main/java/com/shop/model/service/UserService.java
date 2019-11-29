package com.shop.model.service;

import com.shop.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registration(User user);
}
