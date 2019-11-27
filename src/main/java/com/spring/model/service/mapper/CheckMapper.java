package com.spring.model.service.mapper;

import com.spring.model.domain.Check;
import com.spring.model.domain.User;
import com.spring.model.entity.CheckEntity;
import com.spring.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CheckMapper {
    private UserMapper userMapper;

    public Check checkEntityToCheck(CheckEntity checkEntity) {
        if (checkEntity == null) {
            return null;
        }

        User user = userMapper.userEntityToUser(checkEntity.getCreator());

        return Check.builder()
                .id(checkEntity.getId())
                .crtime(checkEntity.getCrtime())
                .total(checkEntity.getTotal())
                .discount(checkEntity.getDiscount())
                .canceled(checkEntity.getCanceled())
                .registration(checkEntity.getRegistration())
                .creator(user)
                .build();
    }

    public CheckEntity checkToCheckEntity(Check check) {
        if (check == null) {
            return null;
        }

        UserEntity user = userMapper.userToUserEntity(check.getCreator());

        return CheckEntity.builder()
                .id(check.getId())
                .crtime(check.getCrtime())
                .total(check.getTotal())
                .discount(check.getDiscount())
                .canceled(check.getCanceled())
                .registration(check.getRegistration())
                .creator(user)
                .build();
    }
}
