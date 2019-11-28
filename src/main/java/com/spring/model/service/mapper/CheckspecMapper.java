package com.spring.model.service.mapper;

import com.spring.model.domain.Check;
import com.spring.model.domain.Checkspec;
import com.spring.model.domain.Goods;
import com.spring.model.entity.CheckEntity;
import com.spring.model.entity.CheckspecEntity;
import com.spring.model.entity.GoodsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class CheckspecMapper {
    private CheckMapper checkMapper;
    private GoodMapper goodMapper;

    public Checkspec checkspecEntityToCheckspec(CheckspecEntity checkspecEntity) {
        if (Objects.isNull(checkspecEntity)) {
            return null;
        }

        Check check = checkMapper.checkEntityToCheck(checkspecEntity.getCheck());
        Goods good = goodMapper.goodEntityToGood(checkspecEntity.getGoods());

        return Checkspec.builder()
                .id(checkspecEntity.getId())
                .quant(checkspecEntity.getQuant())
                .price(checkspecEntity.getPrice())
                .total(checkspecEntity.getTotal())
                .nds(checkspecEntity.getNds())
                .ndstotal(checkspecEntity.getNdstotal())
                .canceled(checkspecEntity.getCanceled())
                .check(check)
                .goods(good)
                .build();
    }

    public CheckspecEntity checkspecToChecksoecEntity(Checkspec checkspec) {
        if (Objects.isNull(checkspec)) {
            return null;
        }

        CheckEntity check = checkMapper.checkToCheckEntity(checkspec.getCheck());
        GoodsEntity good = goodMapper.goodToGoodEntity(checkspec.getGoods());

        return CheckspecEntity.builder()
                .id(checkspec.getId())
                .quant(checkspec.getQuant())
                .price(checkspec.getPrice())
                .total(checkspec.getTotal())
                .nds(checkspec.getNds())
                .ndstotal(checkspec.getNdstotal())
                .canceled(checkspec.getCanceled())
                .check(check)
                .goods(good)
                .build();
    }
}
