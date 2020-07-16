package entity;

public class EquipmentType {
	private String name;
	private int isQuote;
	public EquipmentType(String name) {
		super();
		this.name = name;
		this.isQuote = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsQuote() {
		return isQuote;
	}
	public void setIsQuote(int isQuote) {
		this.isQuote = isQuote;
	}
	
}
