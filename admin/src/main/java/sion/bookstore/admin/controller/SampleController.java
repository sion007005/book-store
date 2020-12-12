package sion.bookstore.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.sample.repository.Sample;
import sion.bookstore.domain.sample.service.SampleService;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class SampleController {
    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/sample/list")
    public ModelAndView getList() {
        List<Sample> sampleList = sampleService.findAll();
        ModelAndView mav = new ModelAndView("sample/list");
        mav.addObject("sampleList", sampleList);
        return mav;
    }
}
