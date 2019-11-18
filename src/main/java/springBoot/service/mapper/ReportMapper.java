package springBoot.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import springBoot.domain.Report;
import springBoot.entity.ReportEntity;

@Component
@Mapper
public interface ReportMapper {
    Report reportEntityToReport(ReportEntity reportEntity);

    ReportEntity reportToReportEntity(Report report);
}
