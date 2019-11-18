package springBoot.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import springBoot.domain.User;
import springBoot.entity.UserEntity;

@Component
@Mapper
public interface UserMapper {
    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(User user);
}
