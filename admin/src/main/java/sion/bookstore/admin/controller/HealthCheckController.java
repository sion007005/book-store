package sion.bookstore.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.admin.ResponseData;

import java.util.Date;

@Controller
public class HealthCheckController {
    @GetMapping("/health")
    @ResponseBody
    public ResponseData health() {
        return ResponseData.success(new Date());
    }
}
