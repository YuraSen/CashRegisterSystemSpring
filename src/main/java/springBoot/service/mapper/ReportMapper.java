package springBoot.service.mapper;

import org.mapstruct.Mapper;
import springBoot.domain.Report;
import springBoot.entity.ReportEntity;

@Mapper
public interface ReportMapper {
    Report reportEntityToReport(ReportEntity reportEntity);

    ReportEntity reportToReportEntity(Report report);
}
