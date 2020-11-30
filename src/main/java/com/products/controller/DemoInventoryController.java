package com.products.controller;

import static com.products.constants.DemoInventoryConstants.LOG_MESSAGE_PREFIX;
import static com.products.constants.DemoInventoryConstants.REQUEST_RECEIVED_MSG;
import static com.products.constants.DemoInventoryConstants.RESPONSE_RECEIVED_MSG;
import static com.products.constants.DemoInventoryConstants.PRODUCT_ID;
import static com.products.constants.DemoInventoryConstants.SEPERATOR;
import static java.lang.String.format;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.model.Products;
import com.products.repository.ProductsRepository;

@RestController
@RequestMapping("/products")
public class DemoInventoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoInventoryController.class);
	
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping(value = "/productDetails")
    public ResponseEntity<List<Products>> getAllProductDetails(){
    	List<Products> products = productsRepository.findAll();
    	LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(RESPONSE_RECEIVED_MSG, products));
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping(value = "/productDetail")
    public ResponseEntity<Optional<Products>> getProductDetailByProductId(@RequestParam Integer productid){
    	LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(PRODUCT_ID, productid));
    	Optional<Products> product = productsRepository.findById(productid);
    	LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(RESPONSE_RECEIVED_MSG, product));
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping(value = "/addProduct",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addProduct(@RequestBody Products product){
    	LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(REQUEST_RECEIVED_MSG, product));
        productsRepository.save(product);
        String message = "New product inserted";
        LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(RESPONSE_RECEIVED_MSG, message));
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping(value = "/updateProduct",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateProduct(@RequestBody Products product){
    	LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(REQUEST_RECEIVED_MSG, product));
        productsRepository.save(product);
        String message = "Product updated";
        LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(RESPONSE_RECEIVED_MSG, message));
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @DeleteMapping(value = "/deleteProduct",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteProduct(@RequestParam Integer productid){
    	LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(PRODUCT_ID, productid));
        productsRepository.deleteById(productid);
        String message = "Product deleted";
        LOGGER.info(LOG_MESSAGE_PREFIX + SEPERATOR + format(RESPONSE_RECEIVED_MSG, message));
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
