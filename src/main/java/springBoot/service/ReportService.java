package springBoot.service;

import springBoot.domain.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Report> findByDate(LocalDate date);

    List<Report> findAll(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
