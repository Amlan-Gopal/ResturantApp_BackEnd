package com.project.ResturantApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ResturantApp.model.Order;
import com.project.ResturantApp.service.OrderService;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addOrder(@Valid @RequestBody Order orderInput) throws Exception {
		Order order = orderService.addOrder(orderInput);
		return new ResponseEntity<Object>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<Order> getAllOrders() throws Exception{
		List<Order> orders = orderService.getAllOrders();
		return orders;
	}
}
