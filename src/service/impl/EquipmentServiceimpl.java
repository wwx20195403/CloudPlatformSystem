package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import service.EquipmentService;
import utils.FileUtils2;
import entity.Equipment;

public class EquipmentServiceimpl implements EquipmentService {

	@Override
	public boolean addEquipment(Equipment equip) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Equipment>> fUtils = new FileUtils2<>();
		List<Equipment> list = fUtils.getData("equipments.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}	
		//write data to file
		list.add(equip);
		fUtils.writeData(list, "equipments.data");	
		return true;
	}
	@Override
	public boolean deleteEquipment(String id) throws IOException {
		FileUtils2<List<Equipment>> fUtils = new FileUtils2<>();
		List<Equipment> list = fUtils.getData("equipments.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
		for(Equipment equip : list) {
			if(equip.getIsAvailable().equals("true")) {
				if(equip.getId().equals(id)) {
					equip.setIsAvailable("false");
					fUtils.writeData(list, "equipments.data");	
					return true;
				}
			}
		}
		}
	return false;
	}
	@Override
	public boolean changeEquipment(Equipment equip) throws IOException {
		FileUtils2<List<Equipment>> fUtils = new FileUtils2<>();
		List<Equipment> list = fUtils.getData("equipments.data");
		
		//update data
		
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(Equipment equips : list) {
				if(equips.getId().equals(equip.getId())) {
					if(equips.getIsAvailable().equals("true")) {	
						equips.setDescription(equip.getDescription());
						equips.setEquiomentState(equip.getEquiomentState());
						equips.setName(equip.getName());
						equips.setIsRent(equip.getIsRent());
						equips.setType(equip.getType());
						equips.setSpecifications(equip.getSpecifications());
						fUtils.writeData(list, "equipments.data");	
						return true;
					}
					
				}
			}	
		}			
		return false;
	}
	@Override
	public Equipment searchEquipment(String id) throws IOException {
		FileUtils2<List<Equipment>> fUtils = new FileUtils2<>();
		List<Equipment> list = fUtils.getData("equipments.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else{
			for(Equipment equip : list) {
			if(equip.getIsAvailable().equals("true")) {
				if(equip.getId().equals(id)) {
					return equip;
				}
			}
		}
		}
	return null;
	}
	@Override
	public List<Equipment> showEquipment() throws IOException {
		FileUtils2<List<Equipment>> fUtils = new FileUtils2<>();
		List<Equipment> list = fUtils.getData("equipments.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();
			return list;
		}
		return list;

	}
	
	
}
