package com.project.ResturantApp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.ResturantApp.entity.CustomerOrder;
import com.project.ResturantApp.entity.Item;
import com.project.ResturantApp.entity.Menu;
import com.project.ResturantApp.exceptions.EntityNotSavedException;
import com.project.ResturantApp.exceptions.NoResourceFoundException;
import com.project.ResturantApp.model.MyItem;
import com.project.ResturantApp.model.Order;
import com.project.ResturantApp.repository.MenuRepository;
import com.project.ResturantApp.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	MenuRepository menuRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Order addOrder(Order orderInput) throws Exception {
		
		CustomerOrder myOrder = saveOrder(orderInput);
		Order order = getOrder(myOrder);	
		
		return order;
	}

	public List<Order> getAllOrders() throws Exception{
		
		List<CustomerOrder> myOrders = orderRepo.findAll();
		if(myOrders == null || myOrders.isEmpty()) {
			throw new NoResourceFoundException("We have no order placed yet!!");
		}
		List<Order> orders = new ArrayList<>();
		for(CustomerOrder myOrder: myOrders) {
			Order order = getOrder(myOrder);
			orders.add(order);
		}
		
		return orders;
	}
	
	private Set<Item> getEntityItems(Set<MyItem> inputItems) throws NoResourceFoundException {
		
		Set<Item> items = new LinkedHashSet<Item>();
		for(MyItem myItem: inputItems) {
			Item item = new Item();
			Menu menu = menuRepo.findById(myItem.getItemID()).get();
			if(menu == null || menu.getItemId() == 0) {
				throw new NoResourceFoundException("Invalid item ID is passed");
			}
			item.setCount(myItem.getCount());
			item.setPrice(myItem.getPrice());
			item.setMenu(menu);
			menu.getItems().add(item);
			items.add(item);
		}
		
		return items;
	}
	
	private CustomerOrder saveOrder(Order orderInput) throws Exception {
		
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomerName(orderInput.getCustomerName());
		
		 orderInput = updateForMessMember(orderInput); 
		
		customerOrder.setTotalPrice(orderInput.getTotalPrice());
		customerOrder.setOrderDate(new Date());
		Set<MyItem> inputItems = orderInput.getItems();
		customerOrder.setItems(getEntityItems(inputItems));	
		
		CustomerOrder myOrder = orderRepo.save(customerOrder);
		
		if(myOrder == null) {
			throw new EntityNotSavedException("The entity could not been saved");
		}
		
		return myOrder;
	}

	private Order getOrder(CustomerOrder inputOrder) {
		
		Order order = new Order();
		
		order.setOrderId(inputOrder.getOrderId());
		order.setCustomerName(inputOrder.getCustomerName());
		order.setTotalPrice(inputOrder.getTotalPrice());
		order.setOrderDate(inputOrder.getOrderDate());
		
		Set<Item> myItems = inputOrder.getItems();
		
		Set<MyItem> items = new LinkedHashSet<MyItem>();
		
		for(Item myItem: myItems) {
			MyItem item = new MyItem();
			item.setItemID(myItem.getMenu().getItemId());
			item.setItemName(myItem.getMenu().getItemName());
			item.setCount(myItem.getCount());
			item.setPrice(myItem.getPrice());
			items.add(item);
		}
		order.setItems(items);
		
		return order;
	}
	
	private Order updateForMessMember(Order orderInput) {
		String customerName = orderInput.getCustomerName();
		double totalPrice = orderInput.getTotalPrice();
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity("http://mess-service/mess/"+customerName, String.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				totalPrice -= 50;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		orderInput.setTotalPrice(totalPrice);
		return orderInput;
	}
}
