package com.spring.model.service.impl;

import com.spring.model.domain.User;
import com.spring.model.domain.UserType;
import com.spring.model.exception.DataNotExistRuntimeException;
import com.spring.model.exception.EntityNotFoundRuntimeException;
import com.spring.model.exception.IdInvalidRuntimeException;
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
    public User findByLogin(String login) {
        return userMapper.userEntityToUser(userRepository.findByLogin(login).
                orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user by this login")));
    }

    @Override
    public User findById(Long id) {
        if (id < 0) {
            log.error("Id not exist");
            throw new IdInvalidRuntimeException("Id not exist");
        }

        return userMapper.userEntityToUser(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user by this id")));
    }

    @Override
    public User save(User user, UserType userType) {
        if (user == null || userType == null) {
            log.error("User don't save, user or userType empty");
            throw new DataNotExistRuntimeException("User don't save, user or userType empty");
        }

        UserType savedUserType = userTypeMapper.userTypeEntityToUserType(userTypeRepository.save(userTypeMapper.userTypeToUserTypeEntity(userType)));
        user.setUserType(savedUserType);
        User result = userMapper.userEntityToUser(userRepository.save(userMapper.userToUserEntity(user)));

        if (Objects.isNull(result)) {
            log.error("User don't save");
            throw new EntityNotFoundRuntimeException("User don't save");
        }
        return result;
    }

    @Override
    public User registration(User user) {
        if (user == null) {
            log.error("User is null");
            throw new DataNotExistRuntimeException("User is null");
        }

        if (!userRepository.findByLogin(user.getLogin()).isPresent()) {
            UserType type = userTypeMapper.userTypeEntityToUserType(userTypeRepository.findByType("cashier")
                    .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user type by this type")));

            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            user.setUserType(type);
            return userMapper.userEntityToUser(userRepository.save(userMapper.userToUserEntity(user)));
        } else {
            log.info("Don't find user by this login");
            throw new EntityNotFoundRuntimeException("Don't find user by this login");
        }
    }
}
