package factory;

import service.BaseService;
import service.impl.EquipmentServiceimpl;
import service.impl.UserServiceImpl;

public class MyServiceFactory {
	 public static BaseService createService(String message) {
			
	    	BaseService  baseService=null;
			
	    	if ("EquipmentService".equals(message))
	    		baseService = new EquipmentServiceimpl();
	    	
	    	
	    	if ("UserService".equals(message))
	    		baseService = new UserServiceImpl();
	    	
	        return baseService;
		}
}
