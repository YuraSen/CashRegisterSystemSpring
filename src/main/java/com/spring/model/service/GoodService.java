package com.spring.model.service;

import com.spring.model.domain.Goods;
import org.springframework.data.domain.Page;

public interface GoodService {
    Goods findByCode(int code);

    Goods findByName(String name);

    Page<Goods> getPageGoods(int currentPage, int pageSize);

    Integer reduceQuant(Long id, double quant);

    void changeGoods(Integer code, Double newQuant, Double newPrice);
}