package com.quizquiz.service;

import java.util.Random;

import com.quizquiz.message.ChannelSelect;
import com.quizquiz.message.ChatSendAll;

public class QuizQuizService {

	public String process(byte[] bData, String userID) throws Exception {
		String message = new String(bData, "euc-kr");
		System.out.println("Recieved message = [" + message + "]");
		
		String opCode = message.substring(0, 2);
		String response = "";
		
		switch(opCode) {
		case "01" :
			response = channelProcess(opCode, bData, userID);
			break;
		case "02" : 
			response = chatProcess(opCode, bData, userID);
			break;
		case "03" : 
			response = AIProcess(message);
		default :
			break;
		}
		
		return response;
	}
	
	private String channelProcess(String opCode, byte[] bData, String userID) throws Exception {
		ChannelSelect channelSelect = new ChannelSelect(bData);
		try {
			ChannelService.joinChannel(Integer.parseInt(channelSelect.getJoinChannel()), userID);
			return channelSelect.getJoinChannel() + "번 채널에 입장하셨습니다";
		} catch (Exception e) {
			if ("ChannelFullException".equals(e.getMessage())) {
				return "채널이 가득찼습니다";
			} else {
				return "알 수 없는 오류. 채널을 다시 선택해 주세요";
			}
		}
	}
	
	private String chatProcess(String opCode, byte[] bData, String userID) throws Exception {
		ChatSendAll chatSendAll = new ChatSendAll(bData);
		try {
			ChatService chatService = new ChatService();
			chatService.MessageSendAll(chatSendAll.getMessage(), ChannelService.getUserChannel(userID));
			return "메시지 전송 성공";
		} catch (Exception e) {
			return "메시지 전송 실패";
		}
	}
	
	private String AIProcess(String message) throws Exception {
		String[] arrString = {"은", "는", "이", "가", "를", "도", "을"};
		String[] arrHello = {"안녕", "오랜만", "오랫만", "잘지냈", "잘지내"};
		String[] resHello = {"그래 너도 안녕", "나야뭐 항상 똑같지", "밥은 먹고 다니냐?", "안녕 못하다"};
		
		Random random = new Random();
		
		for (String text : arrHello) {
			if (message.indexOf(text) > -1) {
				int randomValue = random.nextInt(4);
				return resHello[randomValue];
			} else {
				return "인사를 하라고";
			}
		}
		return "무슨말이야 그게";
	}
}
