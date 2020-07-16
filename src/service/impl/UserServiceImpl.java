package service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.FileUtils2;

import entity.User;
import service.UserService;



public class UserServiceImpl implements UserService {
	@Override
	public boolean validate(String id, String password) {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		String id_1=null;
		String password_1=null;
		for(User user : list) {
			if(user.getIsAvailable().equals("true")) {
				id_1=user.getId();
				password_1=user.getPassword();
				if(id_1==id&&password_1==password) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean addUser(User user) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(User use : list) {
				if(user.getId().equals(use.getId())) {
					if(use.getIsAvailable().equals("true")) {
						return false;
					}
					
				}
			}
		}		
		//write data to file
		list.add(user);
		fUtils.writeData(list, "users.data");	
		return true;

}

	@Override
	public boolean deleteUser(String id) throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
		for(User use : list) {
			if(use.getIsAvailable().equals("true")) {
				if(use.getId().equals(id)) {
					use.setIsAvailable("false");
					fUtils.writeData(list, "users.data");	
					return true;
				}
			}
		}
		}
	return false;
	}

	@Override
	public User searchUser(String id) throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else{
			for(User use : list) {
			if(use.getIsAvailable().equals("true")) {
				if(use.getId().equals(id)) {
					return use;
				}
			}
		}
		}
	return null;
	}

	@Override
	public boolean changeUser(User user) throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(User use : list) {
				if(user.getId().equals(use.getId())) {
					if(use.getIsAvailable().equals("true")) {
						use.setId(user.getId());
						use.setName(user.getName());
						use.setPassword(user.getPassword());
						use.setPhone(user.getPhone());
						use.setType(use.getType());
						fUtils.writeData(list, "users.data");	
						return true;
					}
					
				}
			}	
		}			
		return false;
	}
	
	public List<User> showUser() throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();
			return list;
		}
		return list;
	}
	
}
