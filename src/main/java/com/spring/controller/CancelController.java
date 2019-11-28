package com.spring.controller;

import com.spring.model.domain.Check;
import com.spring.model.domain.Checkspec;
import com.spring.model.domain.Report;
import com.spring.model.service.CheckService;
import com.spring.model.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CancelController {

    private final CheckService checkService;
    private final ReportService reportService;

    @GetMapping("/cancel")
    public String cancelView() {
        return "/cancel";
    }

    @PostMapping(value = "/cancel", params = "btnSearchCheck")
    public String searchCheck(HttpSession session, @RequestParam("checkid") Long checkid) {

        Check check = checkService.findById(checkid);
        List<Checkspec> checkspecs = checkService.findCheckspecByCheck(checkid);

        session.setAttribute("check", check);
        session.setAttribute("checkspecs", checkspecs);
        session.setAttribute("checkfind", checkid);

        return "/cancel";
    }

    @GetMapping("/cancel/edit/{count}")
    public ModelAndView cancelSpec(HttpSession session, @PathVariable Integer count) {
        @SuppressWarnings("unchecked")
        List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("checkspecs");
        checkService.cancelCheckSpec(checkspecs, count);

        return new ModelAndView("redirect:/cancel");
    }

    @PostMapping(value = "/cancel", params = "btnCancelCheck")
    public String cancelCheck(HttpSession session) {
        Check check = (Check) session.getAttribute("check");
        checkService.cancelCheck(check);

        return "/cancel";
    }

    @PostMapping(value = "/cancel", params = "btnXReport")
    public ModelAndView printXReport(HttpSession session) {
        Report xReport = reportService.getDataXReport();
        session.setAttribute("xReport", xReport);
        session.setAttribute("zReport", null);

        return new ModelAndView("redirect:/report");
    }

    @PostMapping(value = "/cancel", params = "btnZReport")
    public ModelAndView printZReport(HttpSession session) {
        Report zReport = reportService.getDataZReport();
        session.setAttribute("zReport", zReport);
        session.setAttribute("xReport", null);

        return new ModelAndView("redirect:/report");
    }
}
