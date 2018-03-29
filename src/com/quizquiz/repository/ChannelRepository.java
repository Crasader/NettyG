package com.quizquiz.repository;

import java.util.HashMap;

import io.netty.channel.Channel;

public class ChannelRepository {

	private static HashMap<String, Channel> channelCTX = new HashMap<String, Channel>();
	
	public static void add(String userID, Channel channel) {
		channelCTX.put(userID, channel);
	}
	
	public static void del(String userID) {
		channelCTX.remove(userID);
	}
	
	public static Channel get(String userID) {
		return channelCTX.get(userID);
	}
}
