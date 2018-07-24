package com.bw.bean;

public class Shop {
	private Integer sid;
	private String sname;
	private String address;
	private Integer id;
	private String tel;
	private String remark;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Shop(Integer sid, String sname, String address, Integer id,
			String tel, String remark) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.address = address;
		this.id = id;
		this.tel = tel;
		this.remark = remark;
	}
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Shop [sid=" + sid + ", sname=" + sname + ", address=" + address
				+ ", id=" + id + ", tel=" + tel + ", remark=" + remark + "]";
	}
	
}
