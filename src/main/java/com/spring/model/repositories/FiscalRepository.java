package com.spring.model.repositories;

import com.spring.model.entity.FiscalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiscalRepository extends JpaRepository<FiscalEntity, Long> {
    List<Object[]> createXReport();

    List<Object[]> createZReport();
}
