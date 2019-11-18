package springBoot.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springBoot.domain.Check;
import springBoot.entity.CheckEntity;
import springBoot.repository.CheckRepository;
import springBoot.service.CheckService;
import springBoot.service.mapper.CheckMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final CheckMapper mapper;

    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository, CheckMapper mapper) {
        this.checkRepository = checkRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Check> findAll(Integer currentPage, Integer recordsPerPage) {
        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<CheckEntity> result = checkRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::checkEntityToCheck)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return checkRepository.count();
    }
}
