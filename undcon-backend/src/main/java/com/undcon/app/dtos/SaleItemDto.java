package com.undcon.app.dtos;

public class SaleItemDto {

	private Long id;

	private String name;

	private Long saleId;

	private String userName;

	private String salesmanName;

	private double price;

	private long quantity;

	private double subTotalItem;
	
	private ItemType itemType;

	public SaleItemDto(Long id, String name, Long saleId, String userName, String salesmanName, double price,
			long quantity, double subTotalItem, ItemType itemType) {
		super();
		this.id = id;
		this.name = name;
		this.saleId = saleId;
		this.userName = userName;
		this.salesmanName = salesmanName;
		this.price = price;
		this.quantity = quantity;
		this.subTotalItem = subTotalItem;
		this.itemType = itemType;
	}

	public SaleItemDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setSubTotalItem(double subTotalItem) {
		this.subTotalItem = subTotalItem;
	}

	public double getSubTotalItem() {
		return subTotalItem;
	}

	public ItemType getItemType() {
		return itemType;
	}
}
