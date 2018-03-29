package com.quizquiz.service;

import java.util.ArrayList;

public class ChannelService {

	private static ArrayList<String>[] bArrayList;
	private static int MAX_CAHNNEL_COUNT = 3;
	private static int MAX_USER_COUNT = 3;
	
	@SuppressWarnings("unchecked")
	public ChannelService() {
		bArrayList = new ArrayList[MAX_CAHNNEL_COUNT];
		
		for (int i=0; i<MAX_CAHNNEL_COUNT; i++) {
			bArrayList[i] = new ArrayList<String>();
		}
	}
	
	/**
	 * @return Available ChannelList
	 */
	public static ArrayList<String> searchChannel() {
		System.out.println("===== 채널 검색 =====");
		ArrayList<String> availChannelList = new ArrayList<String>();
		
		for (int i=0; i<bArrayList.length; i++) {
			if (bArrayList[i].size() < MAX_USER_COUNT) {
				System.out.println("이용 가능한 채널 : " + (i + 1));
				availChannelList.add(String.valueOf(i + 1));
			}
		}
		
		return availChannelList;
	}
	
	public static void joinChannel(int channelNumber, String userID) throws Exception {
		int channelIDX = channelNumber - 1;
		System.out.println("===== 채널 접속 =====");
		System.out.println("요청 채널 : " + channelNumber);
		System.out.println("요청 ID : " + userID);
		System.out.println("현재 채널 인원 수 : " + bArrayList[channelIDX].size() + "/" + MAX_USER_COUNT);
		
		if (bArrayList[channelIDX].size() < MAX_USER_COUNT) {
			bArrayList[channelIDX].add(userID);
		} else {
			throw new Exception("ChannelFullException");
		}
		
		System.out.println("접속 후 채널 인원 수 : " + bArrayList[channelIDX].size() + "/" + MAX_USER_COUNT);
	}
	
	public static void exitChannel(String userID) throws Exception {
		System.out.println("===== 채널 종료 =====");
		System.out.println("종료 유저 : " + userID);
		int channelIDX = getUserChannel(userID);
		System.out.println("접속 종료 채널 : " + channelIDX);
		
		bArrayList[channelIDX - 1].remove(userID);
	}
	
	public static ArrayList<String> getUserList(int channelNumber) {
		ArrayList<String> userList = new ArrayList<String>();
		String printUserList = "";
		System.out.println("===== 채널 유저 리스트 =====");
		System.out.println("요청 채널 : " + channelNumber);
		
		for (String userId : bArrayList[channelNumber - 1]) {
			System.out.println("채널 유저 : " + userId);
			userList.add(userId);
			printUserList = userId + ",";
		}
		
		System.out.println("현재 채널 유저리스트 : " + printUserList);
		return userList;
	}
	
	public static int getUserChannel(String userID) {
		System.out.println("===== 유저 채널 =====");
		System.out.println("요청 유저 : " + userID);
		int channelNumber = 0;
		
		for (int i=0; i<bArrayList.length; i++) {
			if (bArrayList[i].contains(userID)) {
				channelNumber = i + 1;
				System.out.println("채널 : " + (i + 1));
			}
		}
		
		return channelNumber;
	}
}
