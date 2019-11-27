package com.spring.model.service;

import com.spring.model.domain.Goods;
import org.springframework.data.domain.Page;

public interface GoodService {
    Goods findByCode(int code);

    Goods findByName(String name);

    Page<Goods> view(int currentPage, int pageSize);

    Integer reduceQuant(Long id, double quant);

    Long addGoods(Integer code, String name, Double quant, Double price, String measure, String comments);

    void changeGoods(Integer code, Double newQuant, Double newPrice);
}