package com.hs.mina.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println(message.toString());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("idle......");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("close.......aa");
	}

}
