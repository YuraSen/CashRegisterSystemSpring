package com.spring.model.repositories;

import com.spring.model.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
    Optional<GoodsEntity> findByCode(int code);

    Optional<GoodsEntity> findByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("Update GoodsEntity g SET g.quant=g.quant- :quant WHERE g.id=:id")
    Integer reduceQuant(@Param("id") Long id, @Param("quant") double quant);

    long count();
}
