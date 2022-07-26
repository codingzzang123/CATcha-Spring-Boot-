package com.ib.cat.controller.main;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");

        model.addAttribute("status", status);

        if (status.equals(HttpStatus.NOT_FOUND.value())) {
            return "templates/error/404";
        }else {
            return "templates/error/500";
        }
    }
}
