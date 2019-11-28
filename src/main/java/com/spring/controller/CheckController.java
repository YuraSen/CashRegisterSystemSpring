package com.spring.controller;

import com.spring.model.domain.Order;
import com.spring.model.domain.User;
import com.spring.model.service.CheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckController {

    private final CheckService checkService;

    @GetMapping("/check")
    public String viewCheck() {
        return "/check";
    }

    @PostMapping(value = "/check", params = "btnAddOrder")
    public String addOrder(HttpSession session, @ModelAttribute Order order) {
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) session.getAttribute("addOrders");
        if (orders == null) {
            orders = new ArrayList<>();
            session.setAttribute("addOrders", orders);
        }
        Order orderResult = checkService.addOrder(order);
        orders.add(orderResult);

        return "/check";
    }

    @PostMapping(value = "/check", params = "btnCreateCheck")
    public String createCheck(HttpSession session, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) session.getAttribute("addOrders");

        if (!orders.isEmpty()) {
            checkService.addCheck((User) session.getAttribute("user"), orders);
            request.setAttribute("addedCheck", true);
        } else {
            request.setAttribute("addedCheck", false);
        }
        orders.clear();

        return "/check";
    }

    @PostMapping(value = "/check", params = "btnCancelCheck")
    public String clearCheck(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) session.getAttribute("addOrders");
        if (!orders.isEmpty()) {
            orders.clear();
        }

        return "/check";
    }

    @GetMapping("/check/del/{count}")
    public ModelAndView editGoods(HttpSession session, @PathVariable Integer count) {
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) session.getAttribute("addOrders");
        if (!orders.isEmpty()) {
            orders.remove(count - 1);
        }
        return new ModelAndView("redirect:/check");
    }
}
