package com.quizquiz.service;

import java.util.ArrayList;

import com.quizquiz.repository.ChannelRepository;

import io.netty.channel.Channel;

public class ChatService {
	
	public void MessageSendAll(String Message, int channelNumber) throws Exception {
		Channel channel;
		ArrayList<String> userList = ChannelService.getUserList(channelNumber);
		for (String userID : userList) {
			channel = ChannelRepository.get(userID);
			channel.writeAndFlush(Message);
		};
	}
}
