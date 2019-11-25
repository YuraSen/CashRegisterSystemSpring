package com.spring.model.service.mapper;

import com.spring.model.domain.User;
import com.spring.model.domain.UserType;
import com.spring.model.entity.UserEntity;
import com.spring.model.entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class UserMapper {
    private UserTypeMapper userTypeMapper;

    public User userEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserType userType = userTypeMapper.userTypeEntityToUserType(userEntity.getUserType());

        return User.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .userType(userType)
                .build();
    }

    public UserEntity userToUserEntity(User user) {
        if (user == null) {
            return null;
        }

        UserTypeEntity userType = userTypeMapper.userTypeToUserTypeEntity(user.getUserType());

        return UserEntity.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .name(user.getName())
                .userType(userType)
                .build();
    }
}
