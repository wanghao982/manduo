package com.hs.mina.test;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

	public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
		if(session.containsAttribute("WCOutTimeNum")){
			int num = (Integer)session.getAttribute("WCOutTimeNum");
			num++;
			if(num >= 2){
				session.closeNow();
			}
		}else{
			session.setAttribute("WCOutTimeNum",1);
		}
			
	}

}
