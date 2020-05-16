package com.hs.mina.test;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class WCCodecFactory implements ProtocolCodecFactory {
	
	private final WCEncoder encoder;
	private final WCDecoder decoder;
	
	public WCCodecFactory(){
		encoder = new WCEncoder();
		decoder = new WCDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
