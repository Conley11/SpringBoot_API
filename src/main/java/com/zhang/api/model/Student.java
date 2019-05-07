package com.zhang.api.model;

public class Student {
	private String student_id;
	private String phone;
	private String nick_name;
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", phone=" + phone + ", nick_name=" + nick_name + "]";
	}

}
