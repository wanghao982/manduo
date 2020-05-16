package com.hs.mina.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {
	public static IoSession ss;
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		if(ss == null){
			ss = session; 
		}else{
			System.out.println("再次连接是否同一对象:"+ss.equals(session));
		}
		
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println(message.toString());
		String msg = message.toString();
		/*if(msg.equals("{command:keepalive,kp:511}")){
			session.write("{command:keepalive,kp:611}");
			return;
		}*/
			
		session.write("<shoudao>");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("close....");
		System.out.println(session.isClosing());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		
	}
	
	

}
