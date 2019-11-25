package com.spring.controller;

import com.spring.model.domain.Checkspec;
import com.spring.model.domain.User;
import com.spring.model.service.CheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(value = "/check", params = "btnAddCheckspec")
    public String addCheckSpec(Model model, HttpSession session,
                               @RequestParam("code") Integer code, @RequestParam("name") String name,
                               @RequestParam("quant") Double quant, @RequestParam("nds") Integer nds) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (checkspecs == null) {
            checkspecs = new ArrayList<>();
            session.setAttribute("addcheckspecs", checkspecs);
        }
        Checkspec spec = checkService.addCheckSpec(code, name, quant, nds);
        if (spec != null) {
            checkspecs.add(spec);
        } else {
            if (code != null) {
                model.addAttribute("goodsCodeNotFound", code);
            } else {
                model.addAttribute("goodsNameNotFound", name);
            }
        }
        return "/check";
    }

    @PostMapping(value = "/check", params = "btnCreateCheck")
    public String createCheck(HttpSession session, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (checkspecs != null && checkspecs.size() > 0) {
            try {
                checkService.addCheck((User) session.getAttribute("user"), checkspecs);
                request.setAttribute("addedCheck", true);
                checkspecs.clear();
            } catch (Exception e) {
                request.setAttribute("addedCheck", false);
                checkspecs.clear();
                log.error("Ошибка транзакции при добавлении чека и спецификаций. ", e);
            }
        }
        return "/check";
    }

    @PostMapping(value = "/check", params = "btnCancelCheck")
    public String clearCheck(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (checkspecs != null) {
            checkspecs.clear();
        }
        return "/check";
    }

    @GetMapping("/check/del/{count}")
    public ModelAndView editGoods(HttpSession session, @PathVariable Integer count) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (checkspecs.size() >= count && count > 0) {
            checkspecs.remove(count - 1);
        }
        return new ModelAndView("redirect:/check");
    }
}
