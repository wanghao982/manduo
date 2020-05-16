package com.hs.redis.test;

import redis.clients.jedis.JedisPubSub;

public class WCSubscriber extends JedisPubSub {
	
	
	public WCSubscriber(){
	}
	
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("message:"+message);

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("subscribe.....");
		//this.unsubscribe();
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("unsubscribe....");
	}
	
	

}
