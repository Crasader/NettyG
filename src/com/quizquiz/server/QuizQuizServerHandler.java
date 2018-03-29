package com.quizquiz.server;

import java.util.ArrayList;

import com.quizquiz.repository.ChannelList;
import com.quizquiz.repository.ChannelRepository;
import com.quizquiz.service.ChannelService;
import com.quizquiz.service.QuizQuizService;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

//@ChannelHandler.Sharable
public class QuizQuizServerHandler extends ChannelInboundHandlerAdapter {
	
	QuizQuizService quizquizService = new QuizQuizService();
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	String userID = ctx.channel().remoteAddress().toString();
    	ByteBuf msgBuf = (ByteBuf) msg;
    	byte[] bMessage = new byte[msgBuf.readableBytes()];
    	msgBuf.getBytes(msgBuf.readerIndex(), bMessage);
    	
    	ctx.writeAndFlush(quizquizService.process(bMessage, userID));
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    	String userID = ctx.channel().remoteAddress().toString();
		ArrayList<String> availChannelList = ChannelService.searchChannel();
		String availChannelString = "";
		
		for (String availChannel : availChannelList) {
			availChannelString += availChannel + ",";
		}
		
		ChannelRepository.add(userID, ctx.channel());
    	ctx.writeAndFlush("[" + userID + "]님 서버 접속 성공! 채널을 선택해 주세요.");
    	ctx.writeAndFlush("이용 가능한 채널=[" + availChannelString + "]");
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	String userID = ctx.channel().remoteAddress().toString();
    	System.out.println("===== 접속 종료 =====");
    	System.out.println("종료 유저 : " + userID);

		ChannelService.exitChannel(userID);
		ChannelRepository.del(userID);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
