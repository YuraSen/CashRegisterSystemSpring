package com.spring.model.service;

import com.spring.model.domain.User;
import com.spring.model.domain.UserType;

public interface UserService {
    User findByLogin(String login);

    User findById(Long id);

    User save(User user, UserType userType);

    User registration(User user);
}
