package sion.bookstore.admin.controller;

import org.springframework.web.servlet.ModelAndView;
import sion.bookstore.domain.exceptions.ViewRenderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public interface ViewRender {
    public static final String REDIRECT_NAME = "redirect:";
    public static final String JSON_VIEW_NAME = "_json";
    public static final String IMG_VIEW_NAME = "image";

    void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav);

    public default void addHtmlContextHeader(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html; charset=UTF-8"); // "application/json;charset=UTF-8"
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    public default void addStyleSheetContextHeader(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/css; charset=UTF-8"); // "application/json;charset=UTF-8"
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    public default void addJavaScriptContextHeader(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/javascript; charset=UTF-8"); // "application/json;charset=UTF-8"
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    public default void addJsonContextHeader(HttpServletResponse response) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    public default void addImageContextHeader(HttpServletResponse response) {
        response.addHeader("Content-Type", "image/*");
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    public default void closeStream(InputStream in, OutputStream out) {
        try {
            if (Objects.nonNull(in)) {
                in.close();
            }

            if (Objects.nonNull(out)) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            throw new ViewRenderException(e);
        }

    }
}
