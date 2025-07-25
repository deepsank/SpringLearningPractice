package com.springMvcSearch;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SearchController {

    @RequestMapping("user/{userId}/{userName}")
    public String getUserDetail(@PathVariable("userId") int userId, @PathVariable("userName") String userName){
        System.out.println("UserID:- "+ userId);
        System.out.println("UserName:- "+ userName);
//        Integer.parseInt(userName);   //To simulate NumberFormatException
        return "home";
    }

    @RequestMapping("/home")
    public String home(){
        System.out.println("Inside home() method of SearchController");
        String str = null;   //For simulating Exception in SpringMVC using @ExceptionHandler
//        System.out.println(str.length());
        return "home";
    }

    @RequestMapping("/search")
    public RedirectView search(@RequestParam("querybox") String query){

        String url = "https://www.google.com/search?q="+query;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);

        return redirectView;
    }

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
