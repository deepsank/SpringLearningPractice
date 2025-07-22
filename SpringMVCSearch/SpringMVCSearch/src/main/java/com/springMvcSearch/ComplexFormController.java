package com.springMvcSearch;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComplexFormController {

    @RequestMapping("/complex")
    public String showForm(){
        return "complexForm";
    }

    @RequestMapping(path = "/handleform", method = RequestMethod.POST)
    public String formHandler(@ModelAttribute("student") StudentEntity student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "complexForm";
        }
        System.out.println(student);
        return "success";
    }
}
