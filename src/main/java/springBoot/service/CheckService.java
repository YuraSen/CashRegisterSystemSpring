package springBoot.service;

import springBoot.domain.Check;

import java.util.List;

public interface CheckService {

    List<Check> findAll(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}
