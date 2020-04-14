package com.project.ResturantApp.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ResturantApp.entity.Menu;
import com.project.ResturantApp.exceptions.NoResourceFoundException;
import com.project.ResturantApp.model.MyMenu;
import com.project.ResturantApp.repository.MenuRepository;


@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepo;
	
	public LinkedHashSet<MyMenu> getMenuItems() throws Exception {
		LinkedHashSet<MyMenu> menuList = new LinkedHashSet<>();

		List<Menu> menuItems = new ArrayList<Menu>();
		try {
			menuItems = menuRepo.findAll();
			if(menuItems == null || menuItems.isEmpty()) {
				throw new NoResourceFoundException("Menu is empty. We are sorry!!!");
			}
		
		}catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw new NoResourceFoundException("Menu is empty. We are sorry!!!");
		}
		
		for(Menu menu: menuItems) {
			MyMenu myMenu = new MyMenu();
			myMenu = setMenu(menu);
			menuList.add(myMenu);
		}
		
		// exception handling
		return menuList;
	}
	
	private MyMenu setMenu(Menu menu) {
		MyMenu myMenu = new MyMenu();
		myMenu.setItemId(menu.getItemId());
		myMenu.setItemName(menu.getItemName());
		myMenu.setPrice(menu.getPrice());
		myMenu.setImgPath(menu.getImgPath());
		return myMenu;
	}
}
