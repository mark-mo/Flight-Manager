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

import com.gcu.exception.BadLoginException;
import com.gcu.model.ErrorMessage;
import com.gcu.model.User;
import com.gcu.model.UserToken;
import com.gcu.service.IUserService;

/**
 * 
 * @author Mark
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	IUserService service;
	
	/**
	 * @return A form to create a User
	 */
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		return new ModelAndView("loginUser","user", new User());
	}
	
	/**
	 * This method at /loginUser checks if username + password found in database. Sets UserToken in session
	 * 
	 * @param user Model of a User that got logged in
	 * @return Model and View to Display the Posted User
	 */
	@RequestMapping(path="/loginUser",method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request)
	{
		if(result.hasErrors()) {
			return new ModelAndView("loginUser", "user", user);
		}
		
		// Add user to database and return userPage. Return error if already registered.
		
		try {
			UserToken token = service.authenticate(user);
			request.getSession().setAttribute("token", token);
		} catch (BadLoginException e) {
			ErrorMessage error = new ErrorMessage("Login failed", "Username or password was incorrect. Please try again.", "login");
			return new ModelAndView("error", "error", error);
		}
		
		return new ModelAndView("userPage","user",user);
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@Autowired
	public void setUserService(IUserService service)
	{
		this.service = service;
	}
}
