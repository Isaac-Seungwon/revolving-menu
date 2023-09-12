package com.revolving.data.object;

public class MenuChoose {
	private String no;
	private String memberNo;
	private String menuNo;
	private String date;
	
	public MenuChoose(String no, String memberNo, String menuNo, String date) {
		this.no = no;
		this.memberNo = memberNo;
		this.menuNo = menuNo;
		this.date = date;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s", no, memberNo, menuNo, date);
	}
}
