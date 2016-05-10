package com.rest.example.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rest.example.domain.Customer;
import com.rest.example.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-config.xml" })
public class TestRestController {
	
	private static final Logger LOGGER = Logger.getLogger(TestRestController.class);
	
	@Autowired
	private CustomerRepository customerDao;
	
	@Test
	public void testFindAllCustomers() {
		List<Customer> customerList = (List<Customer>) customerDao.findAll();
		LOGGER.info("list = " + customerList);
	}
	
	@Test
	public void testSaveCustomer() {
		Customer entity = new Customer("Gopinathan", "78/23, West Street, Chennai", 30);				
		customerDao.save(entity);
	}

}
