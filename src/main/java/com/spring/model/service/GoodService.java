package com.spring.model.service;

import com.spring.model.domain.Goods;
import org.springframework.data.domain.Page;

public interface GoodService {
    Goods findByCode(int code);

    Goods findByName(String name);

    Goods findById(Long id);

    Page<Goods> view(int currentPage, int pageSize);

    Integer reduceQuant(Long id, double quant);

    void save(Goods goods);

    Long addGoods(Integer code, String name, Double quant, Double price, String measure, String comments);

    public void changeGoods(Integer code, Double newQuant, Double newPrice);
}