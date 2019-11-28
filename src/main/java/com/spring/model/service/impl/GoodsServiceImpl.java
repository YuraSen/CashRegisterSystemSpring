package com.spring.model.service.impl;

import com.spring.model.domain.Goods;
import com.spring.model.entity.GoodsEntity;
import com.spring.model.exception.EntityNotFoundRuntimeException;
import com.spring.model.exception.GoodsIsExistRuntimeException;
import com.spring.model.exception.InvalidDataRuntimeException;
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
import java.util.Objects;
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
    public void addGoods(Integer code, String name, Double quant, Double price, String measure, String comments) {
//        if..
        Optional<GoodsEntity> goodsEntity = goodsRepository.findByCode(code);
        if (goodsEntity.isPresent()) {
            log.warn("Товар с кодом " + code + " уже существует");
            throw new GoodsIsExistRuntimeException("Товар с кодом " + code + " уже существует");
        }

        Goods goods = new Goods();
        goods.setCode(code);
        goods.setName(name);
        goods.setQuant(quant);
        goods.setPrice(price);
        goods.setMeasure(measure);
        goods.setComments(comments);

        goodsRepository.save(goodMapper.goodToGoodEntity(goods));
    }


    @Override
    public void changeGoods(Integer code, Double newQuant, Double newPrice) {
        Goods goods = goodMapper.goodEntityToGood(goodsRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find good by this code")));

        if (Objects.isNull(newQuant) || Objects.isNull(newPrice)) {
            log.warn("Invalid input good data");
            throw new InvalidDataRuntimeException("Invalid input good data");
        }
        goods.setQuant(newQuant);
        goods.setPrice(newPrice);
        goodsRepository.save(goodMapper.goodToGoodEntity(goods));
    }
}
