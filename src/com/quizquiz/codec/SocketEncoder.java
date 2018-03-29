package com.quizquiz.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SocketEncoder extends MessageToByteEncoder<String> {

	@Override
	public void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
		String remoteIP = ctx.channel().remoteAddress().toString();
//		if ("10.200.4.20".equals(remoteIP.substring(1, 12))) {
//			msg = msg + "\n";
//		}
		msg = msg + "\n";
		out.writeBytes(msg.getBytes("euc-kr"));
	}
}
