package studentkitchen.studentkitchen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.services.UserService;


@Controller
public class LoginController{
    

    @Autowired
    private UserService userService;

    @GetMapping(value="/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("user", new User());
        
        return "signup";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String getLoggingPage(){
        return "login";
    }
    

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redAttr){
        User existUser = userService.findUserByEmail(user.getEmail());
        if(existUser != null){
            model.addAttribute("existUser", "User is already exist!");
            return "signup";
        }
        
        else{
            if(bindingResult.hasErrors()){
            return "signup";
            }
            else{
                redAttr.addFlashAttribute("successMessage", "Nice....User has been registered successfully, You can login now ");
                userService.saveUser(user);
                return "redirect:/login";
            }
        }
    }




}