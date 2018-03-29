package com.quizquiz.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class SocketDecoder extends ByteToMessageDecoder {
	
	private static final String CODE_CHANNEL_SELECT = "01";
	private static final String CODE_CHAT_ALL = "02";
	private static final int CHANNEL_SELECT = 15;
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= CHANNEL_SELECT) {
        	out.add(in.readBytes(CHANNEL_SELECT));
        } else {
        	return;
        }
    }	
}
