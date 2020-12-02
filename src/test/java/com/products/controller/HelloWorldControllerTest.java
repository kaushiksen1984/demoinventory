package com.products.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldControllerTest {
	
	@InjectMocks
	private HelloWorldController helloWorldController;
	
	@Before
	public void setUp() {

	}
	
	@Test
	public void testGetAllProductDetails() {
		String expected = "Hello World";
		ResponseEntity<String> actual = helloWorldController.displayGreetingMessage();
		Assert.assertEquals(expected, actual.getBody());
		Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}
