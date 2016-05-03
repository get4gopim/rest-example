package com.rest.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.example.domain.Customer;
import com.rest.example.domain.CustomerList;

@Controller
public class RestProviderController {

	private static final Logger LOGGER = Logger.getLogger(RestProviderController.class);
	
	public List<Customer> listCustomer = new ArrayList<Customer>();
	
	public RestProviderController() {
		this.listCustomer = findCustomers();
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody CustomerList getAllCustomers() {
		LOGGER.debug("Provider has received request to getAllCustomers");

		CustomerList result = new CustomerList();
		result.setListCustomer(listCustomer);

		LOGGER.debug("return the results");
		return result;
	}
	
	@RequestMapping(value = "/customers.json", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody CustomerList getAllCustomersAsJson() {
		LOGGER.debug("Provider has received request to getAllCustomers");
		
		CustomerList result = new CustomerList();
		result.setListCustomer(listCustomer);

		LOGGER.debug("return the results");
		return result;
	}
	
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody Customer findByCustomerId(@PathVariable("id") int id, HttpServletResponse response) {
		LOGGER.debug("Provider has received request to findByCustomerId");
		
		Customer result = findById(id);
		
		LOGGER.debug("return the results");
		return result;
	}
	
	private List<Customer> findCustomers() {
		Customer customer = new Customer("Gopinathan", "33/5, Anjappar Street, Kolathur", 32);
		customer.setId(1);
		listCustomer.add(customer);
		
		customer = new Customer("Arjun", "33/5, Vel Street, Mettur", 32);
		customer.setId(2);
		listCustomer.add(customer);
		
		return listCustomer;
	}
	
	private Customer findById(int id) {
		Customer customer = new Customer();
		
		for (Customer cust : listCustomer) {
			if (cust.getId() == id) {
				customer = cust;
			}
		}
		
		return customer;
	}
}