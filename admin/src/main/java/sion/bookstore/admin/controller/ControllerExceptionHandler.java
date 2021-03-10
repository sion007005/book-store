package sion.bookstore.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import sion.bookstore.admin.ResponseData;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public final ResponseData exception(Exception ex, WebRequest request) {
        return ResponseData.fail(ex.getMessage());
    }
}
