package com.laura.carpaciu.exceptionhandlers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalErrorController implements ErrorController {


    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {

        if (request.getAttribute("javax.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
    }



    @Override
    public String getErrorPath() {
        return null;
    }
}