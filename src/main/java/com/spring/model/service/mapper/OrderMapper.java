package com.spring.model.service.mapper;

import com.spring.model.domain.Check;
import com.spring.model.domain.Order;
import com.spring.model.domain.Goods;
import com.spring.model.entity.CheckEntity;
import com.spring.model.entity.OrderEntity;
import com.spring.model.entity.GoodsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class OrderMapper {
    private CheckMapper checkMapper;
    private GoodMapper goodMapper;

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        if (Objects.isNull(orderEntity)) {
            return null;
        }

        Check check = checkMapper.checkEntityToCheck(orderEntity.getCheck());
        Goods good = goodMapper.goodEntityToGood(orderEntity.getGoods());

        return Order.builder()
                .id(orderEntity.getId())
                .quant(orderEntity.getQuant())
                .price(orderEntity.getPrice())
                .total(orderEntity.getTotal())
                .nds(orderEntity.getNds())
                .ndstotal(orderEntity.getNdstotal())
                .canceled(orderEntity.getCanceled())
                .check(check)
                .goods(good)
                .build();
    }

    public OrderEntity orderToOrderEntity(Order order) {
        if (Objects.isNull(order)) {
            return null;
        }

        CheckEntity check = checkMapper.checkToCheckEntity(order.getCheck());
        GoodsEntity good = goodMapper.goodToGoodEntity(order.getGoods());

        return OrderEntity.builder()
                .id(order.getId())
                .quant(order.getQuant())
                .price(order.getPrice())
                .total(order.getTotal())
                .nds(order.getNds())
                .ndstotal(order.getNdstotal())
                .canceled(order.getCanceled())
                .check(check)
                .goods(good)
                .build();
    }
}
