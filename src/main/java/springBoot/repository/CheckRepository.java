package springBoot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springBoot.entity.CheckEntity;

@Repository
public interface CheckRepository extends JpaRepository<CheckEntity, Long> {
    Page<CheckEntity> findAll(Pageable pageable);
}


