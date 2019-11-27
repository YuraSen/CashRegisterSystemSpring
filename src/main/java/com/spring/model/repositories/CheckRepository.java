package com.spring.model.repositories;

import com.spring.model.entity.CheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface CheckRepository extends JpaRepository<CheckEntity, Long> {
    @Transactional
    @Modifying
    @Query("Update CheckEntity c SET c.registration=1 WHERE DATE(crtime) = DATE(:date)")
    void register(Date date);
}
