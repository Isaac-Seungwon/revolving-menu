package com.revolving.data.object;

public class Category {
	private String no;
	private String cookery;
	
	public Category(String no, String cookery) {
		this.no = no;
		this.cookery = cookery;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCookery() {
		return cookery;
	}

	public void setCookery(String cookery) {
		this.cookery = cookery;
	}

	@Override
	public String toString() {
		return String.format("%s,%s", no, cookery);
	}
}
