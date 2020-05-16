package com.hs.mina.test;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class WCDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		
		int packHeadLenth = 4;
        if(in.remaining() > packHeadLenth){
            in.mark();
            int len = in.getInt();
            if(in.remaining() <len) {
                in.reset();
                return false;
            }else{

            	byte[] bytes = new byte[len]; 
                in.get(bytes, 0, len);   
                String str = new String(bytes,"UTF-8"); 
                out.write(str);
                if(in.remaining() > 0){
                    return true;
                }
            }
        }
        return false;
	}

}
