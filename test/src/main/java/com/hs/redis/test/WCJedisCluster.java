package com.hs.redis.test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class WCJedisCluster {
	private JedisCluster jc;
	private static WCJedisCluster instance;
	
	private WCJedisCluster(){
		String clusterIp = "192.168.117.133:7001,192.168.117.133:7002,"
				+ "192.168.117.139:7001,192.168.117.139:7002,192.168.117.140:7001,192.168.117.140:7002";
		String[] IPPort = clusterIp.split(",");
		Set<HostAndPort> jedisNodes = new HashSet<HostAndPort>();
		try {
			for(int i=0;i<IPPort.length;i++){
				String[] hp = IPPort[i].split(":");
				jedisNodes.add(new HostAndPort(hp[0],Integer.parseInt(hp[1])));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		jc = new JedisCluster(jedisNodes,poolConfig);
	}
	
	public static WCJedisCluster getInstance(){
		if(instance == null)
			instance = new WCJedisCluster();
		return instance;
	}
	
	public JedisCluster getJedisCluster(){
		return jc;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("test begin....");
			JedisCluster jc = WCJedisCluster.getInstance().getJedisCluster();
			jc.subscribe(new WCSubscriber(), "testsub");
			System.out.println("test aaaaa......");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("finally.....");
			
		}
	}
	
	
	
}
