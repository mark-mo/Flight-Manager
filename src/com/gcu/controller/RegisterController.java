  package com.gcu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.exception.AlreadyRegisteredException;
import com.gcu.model.ErrorMessage;
import com.gcu.model.User;
import com.gcu.model.UserProfile;
import com.gcu.model.UserToken;
import com.gcu.service.IUserService;

/**
 * User controller to handle my login form
 * 
 * @author Mark
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	IUserService service;
	
	/**
	 * @return A form to create a User
	 */
	@RequestMapping(path="/register",method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		return new ModelAndView("registerUser","userProfile",new UserProfile());
	}
	
	/**
	 * This method at /addUser adds a User to the database
	 * 
	 * @param user Model of a User that got posted
	 * @return Model and View to Display the Posted User
	 */
	@RequestMapping(path="/registerUser",method=RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("userProfile") UserProfile user, BindingResult result, HttpServletRequest request)
	{
		if(result.hasErrors()) {
			return new ModelAndView("registerUser", "userProfile", user);
		}
		
		// RegisterDAO make sure that user is not already in the database
		// If they are not, put them in
		
		try {
			UserToken token = service.register(user);
			request.getSession().setAttribute("token", token);
		} catch (AlreadyRegisteredException e) {
			String message = "That username has already been registered. Please pick a new one a try again.";
			ErrorMessage error = new ErrorMessage("Registration failed", message, "register");
			return new ModelAndView("error", "error", error);
		}
		
		return new ModelAndView("userPage","user",user);
	}
	
	@Autowired
	public void setUserService(IUserService service)
	{
		this.service = service;
	}
}
