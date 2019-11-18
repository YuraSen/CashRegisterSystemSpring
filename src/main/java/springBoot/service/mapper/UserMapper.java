package springBoot.service.mapper;

import org.mapstruct.Mapper;
import springBoot.domain.User;
import springBoot.entity.UserEntity;

@Mapper
public interface UserMapper {
    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(User user);
}
