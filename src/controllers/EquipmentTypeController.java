package controllers;

import java.io.IOException;
import java.util.List;

import entity.EquipmentType;
import factory.MyServiceFactory;

public class EquipmentTypeController extends BaseController {
	private EquipmentTypeController equipmentTypeController;
	public EquipmentTypeController(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		equipmentTypeController=(EquipmentTypeController)MyServiceFactory.createService(message);
	}
	public boolean addEquipmentType(EquipmentType equtype) throws IOException{
		return equipmentTypeController.addEquipmentType(equtype);
	}
	public boolean deleteEquipmentType(String id) throws IOException{
		return equipmentTypeController.deleteEquipmentType(id);
	}
	public boolean changeEquipmentType(EquipmentType equtype) throws IOException{
		return equipmentTypeController.changeEquipmentType(equtype);
	}
	public EquipmentType searchEquipmentType(String name) throws IOException{
		return equipmentTypeController.searchEquipmentType(name);
	}
	public List<EquipmentType> showEquipmentType() throws IOException{
		return equipmentTypeController.showEquipmentType();
	}

}
