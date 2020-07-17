package controllers;

import java.io.IOException;
import java.util.List;

import entity.ProductType;
import factory.MyServiceFactory;

public class ProductTypeController extends BaseController {
	private ProductTypeController productTypeController;
	public ProductTypeController(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		productTypeController=(ProductTypeController)MyServiceFactory.createService(message);
	}
	public boolean addProductType(ProductType protype) throws IOException{
		return productTypeController.addProductType(protype);
	}
	public boolean deleteProductType(String id) throws IOException{
		return productTypeController.deleteProductType(id);
	}
	public boolean changeProductType(ProductType protype) throws IOException{
		return productTypeController.changeProductType(protype);
	}
	public ProductType searchProductType(String name) throws IOException{
		return productTypeController.searchProductType(name);
	}
	public List<ProductType> showProductType() throws IOException{
		return productTypeController.showProductType();
	}
}
