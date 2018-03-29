package com.quizquiz.repository;

import java.util.ArrayList;

public class ChannelList {

	private ArrayList<String> userList1;
	private ArrayList<String> userList2;
	
	public ChannelList() { }
	
	public ChannelList(ArrayList<String> userList1, ArrayList<String> userList2) {
		super();
		this.userList1 = userList1;
		this.userList2 = userList2;
	}

	public ArrayList<String> getUserList1() {
		return userList1;
	}

	public void setUserList1(ArrayList<String> userList1) {
		this.userList1 = userList1;
	}

	public ArrayList<String> getUserList2() {
		return userList2;
	}

	public void setUserList2(ArrayList<String> userList2) {
		this.userList2 = userList2;
	}

	@Override
	public String toString() {
		return "ChannelList [userList1=" + userList1 + ", userList2=" + userList2 + "]";
	}
}
