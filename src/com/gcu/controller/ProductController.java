package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.exception.BadLoginException;
import com.gcu.exception.DataNotFoundException;
import com.gcu.model.CompanyToken;
import com.gcu.model.ErrorMessage;
import com.gcu.model.Flight;
import com.gcu.model.Message;
import com.gcu.service.IProductService;

/**
 * 
 * @author Mark
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	IProductService service;
	
	/**
	 * @return A page with a flight's information
	 */
	@RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewFlight(@PathVariable String id) {
		int flightID;
		Flight flight;
		
		try
		{
			flightID = Integer.parseInt(id);
			flight = service.viewFlight(flightID);
		}
		catch (NumberFormatException e)
		{
			ErrorMessage error = new ErrorMessage("Invalid ID", "Flight ID cannot be resolved.", "../flights");
			return new ModelAndView("error", "error", error);
		}
		catch (DataNotFoundException e)
		{
			ErrorMessage error = new ErrorMessage("Flight not found", "No flight with that ID exists.", "../flights");
			return new ModelAndView("error", "error", error);
		}
		
		return new ModelAndView("viewFlight", "flight", flight);
	}
	
	/**
	 * @return A form to create a flight
	 */
	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public ModelAndView displayForm() {
		return new ModelAndView("createFlight", "flight", new Flight());
	}

	/**
	 * This method at /createFl adds a Flight to the database
	 * 
	 * @param flight Model of a Flight that got posted
	 * @return Model and View to Display the Posted Flight
	 */
	@RequestMapping(path = "/createFl", method = RequestMethod.POST)
	public ModelAndView addFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("createFlight", "flight", new Flight());
		}

		// Add flight to database and get its ID
		CompanyToken token = (CompanyToken) request.getSession().getAttribute("companyToken");
		int flightID = service.create(flight, token.getId());
		
		// Redirect to product page of newly added plane
		String URL = "/product/view/" + flightID;
		return new ModelAndView("redirect:" + URL);
	}

	/**
	 * @return A form with a table of all flights
	 */
	@RequestMapping(path = "/flights", method = RequestMethod.GET)
	public ModelAndView viewFlights(HttpServletRequest request) {
		CompanyToken token = new CompanyToken();
		if((token = (CompanyToken) request.getSession().getAttribute("companyToken")) != null) {
			// Does not need an array of all of the flights since it gets it from the Rest service using Ajax
			return new ModelAndView("flightTable","company",token);
		}
		else
			token = new CompanyToken();
			return new ModelAndView("flightTable","company",token);
	}
	
	/**
	 * @return A form to update a flight's arrival and departure times
	 */
	@RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView displayUpdateForm(@PathVariable String id, HttpServletRequest request) {
		int flightID;
		Flight flight;
		
		// TODO check if Flight belongs to company ID in companyToken
		
		try
		{
			flightID = Integer.parseInt(id);
			flight = service.viewFlight(flightID);
		}
		catch (NumberFormatException e)
		{
			ErrorMessage error = new ErrorMessage("Invalid ID", "Flight ID cannot be resolved.", "../flights");
			return new ModelAndView("error", "error", error);
		}
		catch (DataNotFoundException e)
		{
			ErrorMessage error = new ErrorMessage("Flight not found", "No flight with that ID exists.", "../flights");
			return new ModelAndView("error", "error", error);
		}

		return new ModelAndView("updateFlight", "flight", flight);
	}
	
	/**
	 * This method at /updateFl adds a Flight to the database
	 * 
	 * @param flight Model of a Flight that got posted
	 * @return Model and View to Display the Posted Flight
	 */
	@RequestMapping(path = "/updateFl", method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("flight") Flight flight, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("updateFlight", "flight", flight);
		}

		try 
		{
			service.update(flight);
		}
		catch (DataNotFoundException e)
		{
			ErrorMessage error = new ErrorMessage("Update failed", "No flights modified.", "../flights");
			return new ModelAndView("error", "error", error);
		}

		// Redirect to just updated flight
		String URL = "/product/view/" + flight.getId();
		return new ModelAndView("redirect:" + URL);
	}
	
	/**
	 * @return Deletion success page (or error page)
	 * @param ID of flight to delete
	 */
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String id, HttpServletRequest request) {
		int flightID;
		
		// Passes the id of the flight in the the service layer to get deleted
		try
		{
			flightID = Integer.parseInt(id);
			service.delete(flightID);
		}
		catch (NumberFormatException e)
		{
			ErrorMessage error = new ErrorMessage("Invalid ID", "Flight ID cannot be resolved.", "../flights");
			return new ModelAndView("error", "error", error);
		}
		catch (DataNotFoundException e)
		{
			ErrorMessage error = new ErrorMessage("Flight not found", "No flight with that ID exists.", "../flights");
			return new ModelAndView("error", "error", error);
		}
		
		Message result = new Message("Deletion Success", "Flight #"+flightID+" was deleted successfully", "../flights");
		return new ModelAndView("result", "result", result);
	}
}
