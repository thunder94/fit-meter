package pl.jakub.fitmeter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.jakub.fitmeter.model.User;
import pl.jakub.fitmeter.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public RegistrationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@GetMapping
	public ModelAndView showRegistrationForm(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView checkRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, 
			HttpServletRequest request) {
		
		User usernameExists = userService.findByUsername(user.getUsername());
		User emailExists = userService.findByEmail(user.getEmail());
		
		if (usernameExists != null) {
			modelAndView.addObject("errorMessage", "Username already taken");
			modelAndView.setViewName("register");
			bindingResult.reject("username");
		} else if(emailExists != null) {
			modelAndView.addObject("errorMessage", "There is already a user registered with the email provided");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
		
        if (bindingResult.hasErrors()) {
        	modelAndView.setViewName("register");
        } else {
        	
        	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        	System.out.println(user.getName() + " " + user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
		    userService.saveUser(user);
		    
			modelAndView.addObject("confirmationMessage", "Registration successful");
			modelAndView.setViewName("register");
        }

        return modelAndView;
    }
	
}
