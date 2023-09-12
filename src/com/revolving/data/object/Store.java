package com.revolving.data.object;

public class Store {
	private String no;
	private String name;
	private String menuNo;
	private String address;
	private String tel;
	
	public Store(String no, String name, String menuNo, String address, String tel) {
		this.no = no;
		this.name = name;
		this.menuNo = menuNo;
		this.address = address;
		this.tel = tel;
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

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s", no, name, menuNo, address, tel);
	}
}
