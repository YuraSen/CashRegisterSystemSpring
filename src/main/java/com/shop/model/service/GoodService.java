package com.shop.model.service;

import com.shop.model.domain.Goods;
import org.springframework.data.domain.Page;

public interface GoodService {

    Goods findByCode(int code);

    Page<Goods> getPageGoods(int currentPage, int pageSize);

    Integer reduceQuant(Long id, double quant);

    void addGoods(Goods good);

    void changeGoods(Integer code, Double newQuant, Double newPrice);
}