package com.shop.model.service.impl;

import com.shop.model.domain.User;
import com.shop.model.domain.UserType;
import com.shop.model.entity.UserEntity;
import com.shop.model.exception.EntityNotFoundRuntimeException;
import com.shop.model.exception.InvalidDataRuntimeException;
import com.shop.model.repositories.UserRepository;
import com.shop.model.repositories.UserTypeRepository;
import com.shop.model.service.UserService;
import com.shop.model.service.mapper.UserMapper;
import com.shop.model.service.mapper.UserTypeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserType(type);

        UserEntity entity = userMapper.userToUserEntity(user);
        UserEntity userEntity = userRepository.save(entity);
        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        return userMapper.userEntityToUser(userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user by this email")));
    }
}
