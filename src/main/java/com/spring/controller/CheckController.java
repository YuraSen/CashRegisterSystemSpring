package com.spring.controller;

import com.spring.model.domain.Checkspec;
import com.spring.model.domain.User;
import com.spring.model.service.CheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String addCheckSpec(HttpSession session,
                               @RequestParam("code") Integer code,
                               @RequestParam("quant") Double quant, @RequestParam("nds") Integer nds) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (checkspecs == null) {
            checkspecs = new ArrayList<>();
            session.setAttribute("addcheckspecs", checkspecs);
        }
        Checkspec spec = checkService.addCheckSpec(code, quant, nds);
        checkspecs.add(spec);

        return "/check";
    }

    @PostMapping(value = "/check", params = "btnCreateCheck")
    public String createCheck(HttpSession session, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");

        if (!checkspecs.isEmpty()) {
            checkService.addCheck((User) session.getAttribute("user"), checkspecs);
            request.setAttribute("addedCheck", true);
        } else {
            request.setAttribute("addedCheck", false);
        }
        checkspecs.clear();

        return "/check";
    }

    @PostMapping(value = "/check", params = "btnCancelCheck")
    public String clearCheck(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (!checkspecs.isEmpty()) {
            checkspecs.clear();
        }

        return "/check";
    }

    @GetMapping("/check/del/{count}")
    public ModelAndView editGoods(HttpSession session, @PathVariable Integer count) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
        if (!checkspecs.isEmpty()) {
            checkspecs.remove(count - 1);
        }
        return new ModelAndView("redirect:/check");
    }
}
