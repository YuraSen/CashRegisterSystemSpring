package springBoot.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springBoot.domain.Report;
import springBoot.entity.ReportEntity;
import springBoot.exception.InvalidDataForPaginationRuntimeException;
import springBoot.exception.InvalidDataRuntimeException;
import springBoot.repository.ReportRepository;
import springBoot.service.ReportService;
import springBoot.service.mapper.ReportMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper mapper;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper mapper) {
        this.reportRepository = reportRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Report> findByDate(LocalDate date) {
        if (Objects.isNull(date)) {
            log.warn("Date report is null");
            throw new InvalidDataRuntimeException("Date report is null");
        }

        List<ReportEntity> result = reportRepository.findByDate(date);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::reportEntityToReport)
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> findAll(Integer currentPage, Integer recordsPerPage) {
        if (currentPage <= 0 || recordsPerPage <= 0) {
            log.error("Invalid number of report pagination");
            throw new InvalidDataForPaginationRuntimeException("Invalid number of report pagination");
        }

        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<ReportEntity> result = reportRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::reportEntityToReport)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return reportRepository.count();
    }
}