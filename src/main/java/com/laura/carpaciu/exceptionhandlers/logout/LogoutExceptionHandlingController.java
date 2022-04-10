package com.laura.carpaciu.exceptionhandlers.logout;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.laura.carpaciu.errors.client.LogoutException;

@ControllerAdvice
public class LogoutExceptionHandlingController {


    @ExceptionHandler(LogoutException.class)
    public String processLogoutException(LogoutException e){
        e.printStackTrace();

        return "redirect:/logout3";
    }

}
