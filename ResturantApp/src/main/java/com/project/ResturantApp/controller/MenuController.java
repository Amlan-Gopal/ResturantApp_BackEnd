package com.project.ResturantApp.controller;

import java.util.LinkedHashSet; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ResturantApp.exceptions.NoResourceFoundException;
import com.project.ResturantApp.model.MyMenu;
import com.project.ResturantApp.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@GetMapping("/items")
	public LinkedHashSet<MyMenu> getAllItems() throws Exception{
		LinkedHashSet<MyMenu> menuList = menuService.getMenuItems();
		return menuList;
	}
	
}
