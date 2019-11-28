package com.spring.model.service;

import com.spring.model.domain.Check;
import com.spring.model.domain.Order;
import com.spring.model.domain.User;

import java.util.List;

public interface CheckService {

    Order addOrder(Order order);

    void addCheck(User user, List<Order> orders);

    Check findById(Long checkId);

    List<Order> findOrderByCheck(Long checkId);

    void cancelOrder(List<Order> orders, Integer count);

    void cancelCheck(Check check);
}
