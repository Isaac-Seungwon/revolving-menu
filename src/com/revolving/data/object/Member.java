package com.revolving.data.object;

public class Member {
	private String no;
	private String name;
	private String id;
	private String pwd;
	private String tel;
	private String birth;

	public Member(String no, String name, String id, String pwd, String tel, String birth) {
		this.no = no;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.tel = tel;
		this.birth = birth;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s", no, name, id, pwd, tel, birth);
	}
}
