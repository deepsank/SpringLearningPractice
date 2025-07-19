package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Hello, inside Home request url !!!");
        /* Adding the dynamic model data to the view (jsp) through the controller */
        model.addAttribute("name","Bruce Wayne");
        model.addAttribute("id",001);

        List<String> paramVeers = new ArrayList<>();
        paramVeers.add("Major Somnath Sharma");
        paramVeers.add("Naik Jadunath Singh");
        paramVeers.add("Flying Officer Nirmal Jit Singh Sekhon");
        paramVeers.add("Captain Manoj Kumat Pandey");
        paramVeers.add("Captain Vikram Batra");
        paramVeers.add("Naib Subedar Bana Singh");
        paramVeers.add("Grenadier Yogendra Singh Yadav");

        model.addAttribute("pvc",paramVeers);

        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("This is about controller request url!!");
        return "about";
    }

    @RequestMapping("/help")
    public ModelAndView help(){
        System.out.println("This is help controller request url!!");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("skill_1","Java Developer");
        modelAndView.addObject("skill_2","DSA");
        LocalDateTime now = LocalDateTime.now();
        modelAndView.addObject("time",now);

        List<String> superheroes = new ArrayList<>();
        superheroes.add("Bruce Wayne-- Batman");
        superheroes.add("Peter Parker-- Spiderman");
        superheroes.add("Shaktiman");
        superheroes.add("Superman");
        superheroes.add("Flash");
        modelAndView.addObject("superheroes", superheroes);
        modelAndView.setViewName("help");
        return modelAndView;
    }
}
