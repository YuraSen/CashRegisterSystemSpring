package com.spring.model.service;

import com.spring.model.domain.Check;
import com.spring.model.domain.Checkspec;
import com.spring.model.domain.User;

import java.util.List;

public interface CheckService {

    Checkspec addCheckSpec(Integer code, Double quant, Integer nds);

    void addCheck(User user, List<Checkspec> checkspecs);

    Check findById(Long checkId);

    List<Checkspec> findCheckspecByCheck(Long checkId);

    void cancelCheckSpec(List<Checkspec> checkspecs, Integer count);

    void cancelCheck(Check check);
}
