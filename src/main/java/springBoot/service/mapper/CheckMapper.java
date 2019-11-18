package springBoot.service.mapper;

import org.mapstruct.Mapper;
import springBoot.domain.Check;
import springBoot.entity.CheckEntity;

@Mapper
public interface CheckMapper {
    Check checkEntityToCheck(CheckEntity checkEntity);

    CheckEntity checkToCheckEntity(Check check);
}
