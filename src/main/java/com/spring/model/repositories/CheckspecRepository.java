package com.spring.model.repositories;

import com.spring.model.entity.CheckEntity;
import com.spring.model.entity.CheckspecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckspecRepository extends JpaRepository<CheckspecEntity, Long> {
    List<CheckspecEntity> findAllByCheck(CheckEntity check);
}
