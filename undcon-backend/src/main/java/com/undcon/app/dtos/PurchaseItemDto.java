package com.undcon.app.dtos;

public class PurchaseItemDto {

	private Long id;

	private String name;

	private Long purchaseId;

	private String userName;

	private double price;

	private long quantity;

	private double subTotalItem;
	
	private ItemType itemType;

	public PurchaseItemDto(Long id, String name, Long purchaseId, String userName, double price,
			long quantity, double subTotalItem, ItemType itemType) {
		super();
		this.id = id;
		this.name = name;
		this.purchaseId = purchaseId;
		this.userName = userName;
		this.price = price;
		this.quantity = quantity;
		this.subTotalItem = subTotalItem;
		this.itemType = itemType;
	}

	public PurchaseItemDto() {
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

	public Long getPurchaseId() {
		return purchaseId;
	}
	
	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
