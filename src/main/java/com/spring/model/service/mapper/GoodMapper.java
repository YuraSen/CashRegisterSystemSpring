package com.spring.model.service.mapper;

import com.spring.model.domain.Goods;
import com.spring.model.entity.GoodsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class GoodMapper {

    public Goods goodEntityToGood(GoodsEntity goodsEntity) {

        if (goodsEntity == null) {
            return null;
        }

        return Goods.builder()
                .id(goodsEntity.getId())
                .code(goodsEntity.getCode())
                .name(goodsEntity.getName())
                .quant(goodsEntity.getQuant())
                .price(goodsEntity.getPrice())
                .measure(goodsEntity.getMeasure())
                .comments(goodsEntity.getComments())
                .build();
    }

    public GoodsEntity goodToGoodEntity(Goods goods) {
        if (goods == null) {
            return null;
        }

        return GoodsEntity.builder()
                .id(goods.getId())
                .code(goods.getCode())
                .name(goods.getName())
                .quant(goods.getQuant())
                .price(goods.getPrice())
                .measure(goods.getMeasure())
                .comments(goods.getComments())
                .build();
    }
}
