package com.spring.controller;

import com.spring.model.domain.Goods;
import com.spring.model.service.GoodService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsController {

    private final GoodService goodsService;

    @GetMapping("/goods")
    public String viewGoods(Model model, @RequestParam("page") int page, @RequestParam("size") int size) {
        addPagination(model, page, size);

        return "/goods";
    }

    @PostMapping("/goods")
    public String addGoods(Model model, @RequestParam("code") Integer code, @RequestParam("name") String name,
                           @RequestParam("quant") Double quant, @RequestParam("price") Double price, @RequestParam("measure") String measure,
                           @RequestParam("comments") String comments,
                           @RequestParam("page") int page, @RequestParam("size") int size) {

        goodsService.addGoods(code, name, quant, price, measure, comments);
        model.addAttribute("addedGood", name);

        addPagination(model, page, size);

        return "/goods";
    }

    @GetMapping("/goods/edit/{code}")
    public String editGoods(Model model, @PathVariable Integer code, @RequestParam("page") int page,
                            @RequestParam("size") int size) {
        model.addAttribute("editCode", code);
        addPagination(model, page, size);

        return "/goods";
    }

    @PostMapping("/goods/edit/{code}")
    public ModelAndView updateGoods(Model model, @PathVariable Integer code,
                                    @RequestParam("changequant") Double changequant, @RequestParam("changeprice") Double changeprice,
                                    @RequestParam("page") int page, @RequestParam("size") int size) {
        goodsService.changeGoods(code, changequant, changeprice);
        addPagination(model, page, size);

        return new ModelAndView("redirect:/goods");
    }

    private void addPagination(Model model, int currentPage, int pageSize) {
        Page<Goods> goods = goodsService.getPageGoods(currentPage, pageSize);
        model.addAttribute("goods", goods);
        model.addAttribute("currentPage", currentPage);
        int totalPages = goods.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
