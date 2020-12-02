package com.products.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class HelloWorldController {
	
	@GetMapping(value = "/message")
    public ResponseEntity<String> displayGreetingMessage(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
