package com.spring.model.service.mapper;

import com.spring.model.domain.UserType;
import com.spring.model.entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class UserTypeMapper {

    public UserType userTypeEntityToUserType(UserTypeEntity userTypeEntity) {
        if (userTypeEntity == null) {
            return null;
        }

        return UserType.builder()
                .id(userTypeEntity.getId())
                .type(userTypeEntity.getType())
                .description(userTypeEntity.getDescription())
                .build();
    }

    public UserTypeEntity userTypeToUserTypeEntity(UserType userType) {
        if (userType == null) {
            return null;
        }

        return UserTypeEntity.builder()
                .id(userType.getId())
                .type(userType.getType())
                .description(userType.getDescription())
                .build();
    }
}
