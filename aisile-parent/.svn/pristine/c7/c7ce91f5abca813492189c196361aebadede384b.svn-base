package com.aisile.content.test.jdeis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDemo {
	/**
	 * 	单机版
	 * 
	 * */
	@Test
	public void jedisTest(){
		//初始化连接
		Jedis jedis = new Jedis("192.168.13.128", 6379);
		//操作
		jedis.set("nameTest", "valueTest");
		System.out.println(jedis.get("name100"));
		jedis.close();
	}
	
	/**
	 * 	单击连接池版
	 * */
		@Test
		public void jedisTestByPool(){
			JedisPool jedisPool = new JedisPool("192.168.13.128", 6379);
			
			Jedis jedis = jedisPool.getResource();
			jedis.set("nameTest1", "valueTest1");
			System.out.println(jedis.get("nameTest1"));
			jedis.close();
			jedisPool.close();
		}
}
