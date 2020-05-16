package com.hs.mina.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class ServerClass {

	public static void main(String[] args) {
		NioSocketAcceptor acceptor = new NioSocketAcceptor(); 
		
		acceptor.setReuseAddress(true); 
		acceptor.getSessionConfig().setReuseAddress(true);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
		acceptor.getSessionConfig().setReceiveBufferSize(1024); 
		acceptor.getSessionConfig().setSendBufferSize(1024); 
		acceptor.getSessionConfig().setTcpNoDelay(true); 
		acceptor.setBacklog(5); 
		Executor threadPool = Executors.newFixedThreadPool(5);
		//acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		
	//	acceptor.getFilterChain().addLast("codec",  
    //            new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
	//    TextLineCodecFactory textLineCodecFactory=new TextLineCodecFactory(Charset.forName("UTF-8"));
	    //在这里设置限制长度
	//                    textLineCodecFactory.setDecoderMaxLineLength(102400);
	//                    textLineCodecFactory.setEncoderMaxLineLength(102400);
	                    //设定这个过滤器将一行一行(/r/n)的读取数据
	//                    acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(textLineCodecFactory));
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new WCCodecFactory()));
		acceptor.getFilterChain().addLast("exector", new ExecutorFilter(threadPool));
		acceptor.setHandler(new ServerHandler());
		try {
			acceptor.bind(new InetSocketAddress(InetAddress.getLocalHost(),6677));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
