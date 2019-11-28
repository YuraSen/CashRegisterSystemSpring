package com.spring.model.service.impl;

import com.spring.model.domain.User;
import com.spring.model.domain.UserType;
import com.spring.model.exception.EntityNotFoundRuntimeException;
import com.spring.model.exception.InvalidDataRuntimeException;
import com.spring.model.repositories.UserRepository;
import com.spring.model.repositories.UserTypeRepository;
import com.spring.model.service.UserService;
import com.spring.model.service.mapper.UserMapper;
import com.spring.model.service.mapper.UserTypeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserMapper userMapper;
    private final UserTypeMapper userTypeMapper;

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = userMapper.userEntityToUser(userRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user by this email")));

        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            log.warn("Password is uncorrected");
            return null;
        }

        return user;
    }

    @Override
    public User registration(User user) {
        if (Objects.isNull(user)) {
            log.error("User is null");
            throw new InvalidDataRuntimeException("User is null");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.info("Don't find user by this email");
            throw new EntityNotFoundRuntimeException("Don't find user by this email");
        }

        UserType type = userTypeMapper.userTypeEntityToUserType(userTypeRepository.findByType("cashier")
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user type by this type")));

        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        user.setUserType(type);
        return userMapper.userEntityToUser(userRepository.save(userMapper.userToUserEntity(user)));
    }
}
