package com.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.products.model.Products;
import com.products.repository.ProductsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class DemoInventoryControllerTest {
	
	@InjectMocks
	private DemoInventoryController demoInventoryController;
	
	@Mock
	private ProductsRepository productsRepository;
	
	private List<Products> expectedProducts = new ArrayList<Products>();
	private Products products;
	
	@Before
	public void setUp() {
		products = new Products();
		products.setProductid(new Integer(1));
		products.setProductname("ABC");
		expectedProducts.add(products);
	}
	
	@Test
	public void testGetAllProductDetails() {
		Mockito.when(productsRepository.findAll()).thenReturn(expectedProducts);
		ResponseEntity<List<Products>> actual = demoInventoryController.getAllProductDetails();
		Assert.assertEquals(expectedProducts, actual.getBody());
		Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
	
	@Test
	public void testAddProduct() {
		String expectedResponse = "New product inserted";
		productsRepository.save(products);
		ResponseEntity<String> actual = demoInventoryController.addProduct(products);
		Assert.assertEquals(expectedResponse, actual.getBody());
		Assert.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
	}
	
	@Test
	public void testUpdateProduct() {
		String expectedResponse = "Product updated";
		productsRepository.save(products);
		ResponseEntity<String> actual = demoInventoryController.updateProduct(products);
		Assert.assertEquals(expectedResponse, actual.getBody());
		Assert.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
	}
	
	@Test
	public void testDeleteProduct() {
		String expectedResponse = "Product deleted";
		productsRepository.deleteById(1);
		ResponseEntity<String> actual = demoInventoryController.deleteProduct(new Integer(1));
		Assert.assertEquals(expectedResponse, actual.getBody());
		Assert.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
	}

}
