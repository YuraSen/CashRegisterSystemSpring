package springBoot.service;

import springBoot.domain.Report;
import springBoot.entity.ReportEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ReportService {
    Set<ReportEntity> findByDate(LocalDate date);

    List<Report> findAll(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
