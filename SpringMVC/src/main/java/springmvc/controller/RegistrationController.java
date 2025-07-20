package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @RequestMapping("/registration")
    // This method handles requests to the "/registration" URL
    public String showRegistrationForm() {
        // Logic to display the Registration form
        return "registration"; // This should return the view name for the registration form
    }

    @RequestMapping(path="/processRegistration", method = RequestMethod.POST)
    public String handleProcessRegistrationForm(
            @RequestParam("email") String userEmail,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("password") String password,
                                                Model model) {
        // Logic to process the registration form data
        // You can save the user information to the database or perform any other necessary actions
        System.out.println("User Email: " + userEmail);
        System.out.println("User Name: " + userName);
        System.out.println("Password: " + password);

        // Add user information to the model
        model.addAttribute("email", userEmail);
        model.addAttribute("userName", userName);
        model.addAttribute("password", password);

        return "registrationSuccess"; // This should return the view name for the registration success page
    }

}
