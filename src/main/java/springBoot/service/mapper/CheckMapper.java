package springBoot.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import springBoot.domain.Check;
import springBoot.entity.CheckEntity;

@Component
@Mapper
public interface CheckMapper {
    Check checkEntityToCheck(CheckEntity checkEntity);

    CheckEntity checkToCheckEntity(Check check);
}
