package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

//    @RequestMapping("/study")
//    public String one(){
//        System.out.println("This is /study handler");
//        return "redirect:/enjoy";
//    }

    @RequestMapping("/study")
    public RedirectView one(){
        System.out.println("This is /study handler");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("enjoy");
//        redirectView.setUrl("https://www.google.com");
        return redirectView;
    }

    @RequestMapping("/enjoy")
    public String two(){
        System.out.println("This is /enjoy handler");
        return "registration";
    }

}
