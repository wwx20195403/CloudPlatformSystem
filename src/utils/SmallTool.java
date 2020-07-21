package utils;

import java.io.IOException;

import controllers.FactoryControllers;
import controllers.UserController;
import entity.Factory;
import entity.User;

public class SmallTool {
	public static void main(String[] args) {
		
	}
	private static FactoryControllers factoryController=new FactoryControllers("FactoryService");
	private static UserController userController=new UserController("UserService");
	//输入id输出名字
	public static String userId_FactotyID(String userID) {
		if(userID.equals("0"))return "产能中心";
		String a=null;
		try {
			a=factoryController.sreachFactory(userID).getName();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	
	public static String  userID_userNumber(String Id) {
		User use = null;
		try {
			use=userController.searchUser(Id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return use.getUserNumber();
	}
	
	
	
}
