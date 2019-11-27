package com.spring.model.service.impl;

import com.spring.model.domain.Goods;
import com.spring.model.entity.GoodsEntity;
import com.spring.model.exception.EntityNotFoundRuntimeException;
import com.spring.model.repositories.GoodsRepository;
import com.spring.model.service.GoodService;
import com.spring.model.service.mapper.GoodMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsServiceImpl implements GoodService {

    private final GoodsRepository goodsRepository;
    private final GoodMapper goodMapper;

    public Goods findByCode(int code) {
        return goodMapper.goodEntityToGood(goodsRepository.findByCode(code)

                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find good by this code")));
    }

    public Goods findByName(String name) {
        return goodMapper.goodEntityToGood(goodsRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find good by this name")));
    }

    @Override
    public Page<Goods> getPageGoods(int currentPage, int pageSize) {
        PageRequest sortedByCode = PageRequest.of(currentPage - 1, pageSize, Sort.by("code"));
        Page<GoodsEntity> allGoodsEntity = goodsRepository.findAll(sortedByCode);
        List<Goods> result = allGoodsEntity
                .stream()
                .map(goodMapper::goodEntityToGood)
                .collect(Collectors.toList());
        return new PageImpl<>(result, sortedByCode, countAllGoods());
    }

    @Override
    public Integer reduceQuant(Long id, double quant) {
        return goodsRepository.reduceQuant(id, quant);
    }

    private long countAllGoods() {
        return goodsRepository.count();
    }

    @Override
    public void changeGoods(Integer code, Double newQuant, Double newPrice) {
        Goods goods = goodMapper.goodEntityToGood(goodsRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find good by this code")));

        if (newQuant != null) {
            goods.setQuant(newQuant);
        }
        if (newPrice != null) {
            goods.setPrice(newPrice);
        }
        goodsRepository.save(goodMapper.goodToGoodEntity(goods));
    }
}
