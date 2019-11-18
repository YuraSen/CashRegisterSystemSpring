package springBoot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.entity.ProductEntity;
import springBoot.entity.ReportEntity;

import java.time.LocalDate;
import java.util.Set;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    Set<ReportEntity> findByDate(LocalDate date);

    Page<ReportEntity> findAll(Pageable pageable);

}
