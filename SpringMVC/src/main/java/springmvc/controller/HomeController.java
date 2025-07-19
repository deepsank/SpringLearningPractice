package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
