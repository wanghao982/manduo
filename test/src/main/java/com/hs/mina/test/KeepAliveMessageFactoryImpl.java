package com.hs.mina.test;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

	public boolean isRequest(IoSession session, Object message) {
		System.out.println(message.toString());
		String msg = message.toString();
		return msg.contains("kp:511");
	}

	public boolean isResponse(IoSession session, Object message) {
		System.out.println(message.toString());
		String msg = message.toString();
		return msg.contains("kp:611");
	}

	public Object getRequest(IoSession session) {
		return "{command:keepalive,kp:511}";
	}

	public Object getResponse(IoSession session, Object request) {
		return null;
	}

}
