package com.hs.mina.test;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class WCEncoder extends ProtocolEncoderAdapter{

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		 IoBuffer buf = IoBuffer.allocate(256).setAutoExpand(true);
		 String msg = message.toString();
		 int num = msg.getBytes(Charset.forName("utf-8")).length;
		 buf.putInt(num);
		 buf.putString(msg,Charset.forName("utf-8").newEncoder());
		 buf.flip();
		 out.write(buf);
	}

	

}
