package com.hs.mina.test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClientClass {

	public static void main(String[] args) {
IoBuffer.setAllocator(new SimpleBufferAllocator());
		
		final NioSocketConnector connect = new NioSocketConnector(); 
		
		connect.getSessionConfig().setReuseAddress(true);
		connect.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		connect.getSessionConfig().setReceiveBufferSize(128); 
		connect.getSessionConfig().setSendBufferSize(128); 
		connect.getSessionConfig().setTcpNoDelay(true); 

		Executor threadPool = Executors.newFixedThreadPool(5);
		//acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		
		//connect.getFilterChain().addLast("codec",  
         //       new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
	//	TextLineCodecFactory textLineCodecFactory=new TextLineCodecFactory(Charset.forName("UTF-8"));
	    //在这里设置限制长度
	//                    textLineCodecFactory.setDecoderMaxLineLength(102400);
	//                    textLineCodecFactory.setEncoderMaxLineLength(102400);
	                    //设定这个过滤器将一行一行(/r/n)的读取数据
	 //                   connect.getFilterChain().addLast("codec", new ProtocolCodecFilter(textLineCodecFactory));
		connect.getFilterChain().addLast("codec", new ProtocolCodecFilter(new WCCodecFactory()));
		
		KeepAliveMessageFactoryImpl kamf = new KeepAliveMessageFactoryImpl();  
         KeepAliveFilter kaf = new KeepAliveFilter(kamf,IdleStatus.BOTH_IDLE,new KeepAliveRequestTimeoutHandlerImpl());//实例化一个 KeepAliveFilter 过滤器  
         kaf.setForwardEvent(false);// 继续调用 IoHandlerAdapter 中的 sessionIdle时间   
         kaf.setRequestInterval(10); //设置当连接的读取通道空闲的时候，心跳包请求时间间隔   
         kaf.setRequestTimeout(5); //设置心跳包请求后 等待反馈超时时间。 超过该时间后则调用KeepAliveRequestTimeoutHandler.CLOSE   
         connect.getFilterChain().addLast("heart",kaf);//该过滤器加入到整个通信的过滤链中  
		
		
		
		
		connect.getFilterChain().addLast("exector", new ExecutorFilter(threadPool));
		connect.setHandler(new ClientHandler());
		
		connect.addListener(new WCConnectListener() {
			
			public void sessionDestroyed(IoSession session) throws Exception {
				while(true){
					Thread.sleep(3000);
					ConnectFuture connectFuture = connect.connect();
					connectFuture.awaitUninterruptibly();
					if(connectFuture.isConnected()){
						System.out.println("重连成功");
						break;
					}else{
						System.out.println("3s后再次连接");
					}
				}
			}
			
			
		});
		
		try {
			connect.setDefaultRemoteAddress(new InetSocketAddress(InetAddress.getLocalHost(),6677));
			ConnectFuture connectFuture = connect.connect();
			connectFuture.awaitUninterruptibly();
			if(connectFuture.isConnected()){
				IoSession session = connectFuture.getSession();
				String strcontent = "{command:login,from:112233@pc,to:123456789,chatid:445544,"
						+ "content:{customers:[{name:wang,age:30},{name:wang,age:30}],"
						+ "msg:this is test}}";
				
				System.out.println(strcontent);
				WriteFuture future = session.write(strcontent);
				future.awaitUninterruptibly();
				session.write(strcontent);
			}else{
				
				System.out.println("连接失败"+InetAddress.getLocalHost().getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	

}
