package com.springMvcSearch;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Centralised Exception Handler */

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public String nullExceptionHandler(Model m){
        m.addAttribute("msg","Null pointer exceptioon has occurred");
        return "exceptionPage";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler( NumberFormatException.class)
    public String numFormatExceptionHandler(Model m){
        m.addAttribute("msg","Number format exceptioon has occurred");
        return "exceptionPage";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler( Exception.class)
    public String genericExceptionHandler(Model m){
        m.addAttribute("msg","Number format exceptioon has occurred");
        return "exceptionPage";
    }
}
