package utils;

import java.io.IOException;

import controllers.FactoryControllers;
import controllers.UserController;
import entity.Factory;
import entity.User;

public class SmallTool {
	private static FactoryControllers factoryController=new FactoryControllers("FactoryService");
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
	
	public static boolean  checkUserID(String a) {
		Factory aa=null;
		try {
			aa=factoryController.sreachFactory(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(aa==null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	
}
