package com.spring.model.service.impl;

import com.spring.model.domain.Check;
import com.spring.model.domain.Checkspec;
import com.spring.model.domain.Goods;
import com.spring.model.domain.User;
import com.spring.model.entity.CheckEntity;
import com.spring.model.entity.CheckspecEntity;
import com.spring.model.exception.*;
import com.spring.model.repositories.CheckRepository;
import com.spring.model.repositories.CheckspecRepository;
import com.spring.model.service.CheckService;
import com.spring.model.service.GoodService;
import com.spring.model.service.mapper.CheckMapper;
import com.spring.model.service.mapper.CheckspecMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckServiceImpl implements CheckService {

    private final CheckRepository checkRepository;
    private final CheckspecRepository checkspecRepository;
    private final CheckMapper checkMapper;
    private final CheckspecMapper checkspecMapper;
    private final GoodService goodsService;

    @Override
    public Checkspec addCheckSpec(Integer code, Double quant, Integer nds) {
        if (Objects.isNull(code) || Objects.isNull(quant) || Objects.isNull(nds)
                || quant < 0 || nds < 0) {
            log.warn("Data for add order is uncorrected");
            throw new InvalidDataRuntimeException("Data for add order is uncorrected");
        }

        Goods goods = goodsService.findByCode(code);
        Checkspec spec = new Checkspec();
        spec.setGoods(goods);
        spec.setQuant(quant);
        spec.setPrice(goods.getPrice());
        spec.setTotal(BigDecimal.valueOf(quant).multiply(BigDecimal.valueOf(spec.getPrice())).doubleValue());
        spec.setNds(nds);
        spec.setNdstotal(BigDecimal.valueOf(spec.getTotal()).multiply(BigDecimal.valueOf(spec.getNds())).divide(new BigDecimal(100)).doubleValue());
        spec.setCanceled(0);

        return spec;
    }

    @Override
    public void addCheck(User user, List<Checkspec> checkspecs) {
        if (user == null) {
            log.warn("User isn't exist");
            throw new InvalidDataRuntimeException("User isn't exist");
        }

        Check check = new Check();
        check.setCreator(user);
        double total = checkspecs.stream().mapToDouble(Checkspec::getTotal).sum();
        check.setTotal(total);
        check.setCanceled(0);
        check = checkMapper.checkEntityToCheck(checkRepository.save(checkMapper.checkToCheckEntity(check)));

        for (Checkspec checkspec : checkspecs) {
            checkspec.setCheck(check);
            Goods goods = checkspec.getGoods();
//            if (goods != null) {
//                goodsService.reduceQuant(goods.getId(), checkspec.getQuant());
//            }
            checkspecRepository.save(checkspecMapper.checkspecToChecksoecEntity(checkspec));
        }
    }

    @Override
    public Check findById(Long checkId) {
        if (checkId < 0) {
            log.warn("Id not exist");
            throw new InvalidIdRuntimeException("Id not exist");
        }

        return checkMapper.checkEntityToCheck(checkRepository.findById(checkId)
                .orElse(null));
    }

    @Override
    public List<Checkspec> findCheckspecByCheck(Long checkId) {
        if (checkId < 0) {
            log.warn("Id not exist");
            throw new InvalidIdRuntimeException("Id not exist");
        }

        Check check = checkMapper.checkEntityToCheck(checkRepository.findById(checkId)
                .orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find check by this id")));

        CheckEntity checkEntity = checkMapper.checkToCheckEntity(check);
        List<CheckspecEntity> checkspecEntities = checkspecRepository.findAllByCheck(checkEntity);

        return checkspecEntities.isEmpty() ?
                Collections.emptyList() :
                checkspecEntities.stream()
                        .map(checkspecMapper::checkspecEntityToCheckspec)
                        .collect(Collectors.toList());
    }

    @Override
    public void cancelCheckSpec(List<Checkspec> checkspecs, Integer count) {
        if (checkspecs.isEmpty() || Objects.isNull(count) || count < 0) {
            log.warn("Order not exist");
            throw new OrderNotExistRuntimeException("Order not exist");
        }

        Checkspec checkspec = checkspecs.get(count - 1);
        checkspec.setCanceled(1);
        checkspecRepository.save(checkspecMapper.checkspecToChecksoecEntity(checkspec));
        double total = checkspecs.stream()
                .filter(spec -> spec.getCanceled() == 0)
                .mapToDouble(Checkspec::getTotal).sum();
        Check check = checkspec.getCheck();
        check.setTotal(total);
        checkRepository.save(checkMapper.checkToCheckEntity(check));
    }

    @Override
    public void cancelCheck(Check check) {
        if (Objects.isNull(check)) {
            log.warn("Check not exist");
            throw new CheckNotExistRuntimeException("Check not exist");
        }

        check.setCanceled(1);
        checkRepository.save(checkMapper.checkToCheckEntity(check));
    }
}
