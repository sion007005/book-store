package sion.bookstore.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class DashboardController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("dashboard");
        return mav;
    }

    @GetMapping(value={"/{path}"})
    public ModelAndView getPath(@PathVariable(required = false) String path) {
//        log.info("path : {}", path);
//        if (StringUtils.isBlank(path)) {
//            path = "index";
//        }
        ModelAndView mav = new ModelAndView(path);
        return mav;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("dashboard");
        return mav;
    }

}
