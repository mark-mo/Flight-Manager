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
import com.gcu.model.Company;
import com.gcu.model.CompanyToken;
import com.gcu.model.ErrorMessage;
import com.gcu.model.User;
import com.gcu.model.UserToken;
import com.gcu.service.ICompanyService;
import com.gcu.service.IUserService;

@Controller
@RequestMapping("/companyLogin")
public class CompanyLoginController {
	ICompanyService service;
	
	/**
	 * @return A form to create a Company
	 */
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		return new ModelAndView("loginCompany","company", new Company());
	}
	
	/**
	 * This method at /loginCompanuy adds a User to the database
	 * 
	 * @param company Model of a Company that got posted
	 * @return Model and View to Display the Posted Company
	 */
	@RequestMapping(path="/loginCompany",method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("company") Company company, BindingResult result, HttpServletRequest request)
	{
		if(result.hasErrors()) {
			return new ModelAndView("loginCompany", "company", company);
		}
		
		// Add user to database and return userPage. Return error if already registered.
		
		try {
			CompanyToken token = service.authenticate(company);
			request.getSession().setAttribute("companyToken", token);
		} catch (BadLoginException e) {
			ErrorMessage error = new ErrorMessage("Company login failed", "Username or password was incorrect. Please try again.", "login");
			return new ModelAndView("error", "error", error);
		}
		
		return new ModelAndView("companyPage","company",company);
	}
	
	@RequestMapping(path="/home",method=RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request)
	{
		CompanyToken token = (CompanyToken) request.getSession().getAttribute("companyToken");
		if (token == null)
		{
			ErrorMessage error = new ErrorMessage("Not logged in", "You must be logged in to do that.", "login");
			return new ModelAndView("error", "error", error);
		}
		
		return new ModelAndView("companyPage", "company", token.getCompany());
	}
	
	@Autowired
	public void setUserService(ICompanyService service)
	{
		this.service = service;
	}
}
