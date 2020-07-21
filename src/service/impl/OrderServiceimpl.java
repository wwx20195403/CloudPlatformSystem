package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.Product;
import service.OrderService;
import utils.FileUtils2;

public class OrderServiceimpl implements OrderService {
	public static void main(String[] args) {
		OrderServiceimpl a=new OrderServiceimpl();
		try {
			System.out.println(a.showOrder());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean addOrder(Order order) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}	

		list.add(order);
		fUtils.writeData(list, "order.data");	
		return true;	
	}

	@Override
	public boolean changeOrder(Order ord) throws IOException {
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}
		for(Order order : list) {
			if(order.getId().equals(ord.getId())) {
				order.setFactoryID(ord.getFactoryID());
				order.setOrdetstate(ord.getOrdetstate());
				fUtils.writeData(list, "order.data");
				return true;
			}
			
		}
		return false;	
	}

	@Override
	public Order searchOrder(String id) throws IOException {
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}
		for(Order order : list) {
			if(order.getId().equals(id)) {
				return order;
			}
			
		}
		return null;
	}

	@Override
	public List<Order> showOrder() throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}
		return list;
	}

}
