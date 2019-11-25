package com.spring.model.repositories;

import com.spring.model.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {

    Optional<GoodsEntity> findByCode(int code);

    Optional<GoodsEntity> findByName(String name);

    @Modifying
    @Query("Update GoodsEntity g SET g.quant=g.quant- :quant WHERE g.id=:id")
    Integer reduceQuant(Long id, double quant);

    long count();
}
