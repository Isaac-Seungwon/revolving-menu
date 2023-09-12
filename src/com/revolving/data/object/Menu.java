package com.revolving.data.object;

public class Menu {
	private String no;
	private String name;
	private String categoryNo;
	private String seasonNo;
	private String price;
	
	public Menu (String no, String name, String categoryNo, String seasonNo, String price) {
		this.no = no;
		this.name = name;
		this.categoryNo = categoryNo;
		this.seasonNo = seasonNo;
		this.price = price;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getSeasonNo() {
		return seasonNo;
	}

	public void setSeasonNo(String seasonNo) {
		this.seasonNo = seasonNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s", no, name, categoryNo, seasonNo, price);
	}
}
