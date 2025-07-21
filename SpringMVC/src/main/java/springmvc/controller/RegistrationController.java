package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springmvc.model.User;
import springmvc.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void commonDataForModel(Model m){
        m.addAttribute("Header", "LearnWith Sankdeep");
        m.addAttribute("Description","Gateway to Dreams");
        System.out.println("Inside commonDataForModel() method, adding common data!!");
    }

    @RequestMapping("/registration")
    // This method handles requests to the "/registration" URL
    public String showRegistrationForm() {
        // Logic to display the Registration form
        System.out.println("Inside showRegistrationForm() method ");
        return "registration"; // This should return the view name for the registration form
    }

   /*

   @RequestMapping(path="/processRegistration", method = RequestMethod.POST)
    public String handleProcessRegistrationForm(
            @RequestParam("email") String userEmail,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("password") String password,
                                                Model model) {
        // Logic to process the registration form data
        // You can save the user information to the database or perform any other necessary actions

        User user = new User();
        user.setUserName(userName);
        user.setEmail(userEmail);
        user.setPassword(password);

        // Add user information to the model
//        model.addAttribute("email", userEmail);
//        model.addAttribute("userName", userName);
//        model.addAttribute("password", password);

        model.addAttribute("user",user);

        return "registrationSuccess"; // This should return the view name for the registration success page
    }

    */

    @RequestMapping(path="/processRegistration", method = RequestMethod.POST)
    public String handleProcessRegistrationForm(@ModelAttribute User user, Model model){
        System.out.println(user);
        this.userService.registerUser(user);
        model.addAttribute("msg","User registered successfully!!!");
        return "registrationSuccess"; // This should return the view name for the registration success page
    }


}
