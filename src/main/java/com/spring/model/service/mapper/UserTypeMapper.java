package com.spring.model.service.mapper;

import com.spring.model.domain.UserType;
import com.spring.model.entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor

public class UserTypeMapper {

    public UserType userTypeEntityToUserType(UserTypeEntity userTypeEntity) {
        if (Objects.isNull(userTypeEntity)) {
            return null;
        }

        return UserType.builder()
                .id(userTypeEntity.getId())
                .type(userTypeEntity.getType())
                .description(userTypeEntity.getDescription())
                .build();
    }

    UserTypeEntity userTypeToUserTypeEntity(UserType userType) {
        if (Objects.isNull(userType)) {
            return null;
        }

        return UserTypeEntity.builder()
                .id(userType.getId())
                .type(userType.getType())
                .description(userType.getDescription())
                .build();
    }
}
