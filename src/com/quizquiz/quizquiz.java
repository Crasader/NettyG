package com.quizquiz;

import com.quizquiz.codec.SocketDecoder;
import com.quizquiz.codec.SocketEncoder;
import com.quizquiz.server.QuizQuizServerHandler;
import com.quizquiz.service.ChannelService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class quizquiz {

	public static void main(String[] args) {
		int port = 8080;
		int bossThread = 1;
		int workerThread = 2;
		
		new ChannelService();
		
		EventLoopGroup parentGroup = new NioEventLoopGroup(bossThread);
		EventLoopGroup childGroup = new NioEventLoopGroup(workerThread);
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(parentGroup, childGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.DEBUG))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					ChannelPipeline cp = sc.pipeline();
					cp.addLast(new DelimiterBasedFrameDecoder(1024*1024, Delimiters.lineDelimiter()))
					  .addLast("SocketDecoder", new SocketDecoder())
					  .addLast("SocketEncoder", new SocketEncoder())
					  .addLast("QuizQuizServerHandler", new QuizQuizServerHandler());
				}
			});

			ChannelFuture cf = sb.bind(port).sync();
			cf.channel().closeFuture().sync();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}

}
