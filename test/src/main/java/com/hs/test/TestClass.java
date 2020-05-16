package com.hs.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class TestClass {
	private static Integer count = 0;
	public static void main(String[] args) throws Exception {
		/*Properties prop = new Properties();
		InputStream is = TestClass.class.getResourceAsStream("test.properties");
		try {
			prop.load(is);
			System.out.println(prop.getProperty("test.wh"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		/*byte[] b = new byte[10];
		b[0] = -127;
		System.out.println(b[0]);
		System.out.println(b[0]+256);
		System.out.println(b[0] & 0Xff);
		int a = b[0];
		System.out.println(a);*/
		test1();
	}
	static void test1() throws Exception{
        Long starTime = System.currentTimeMillis();
        Runnable runnable = () -> {
            for (int i = 0; i < 100000000; i++){
            	TestClass.count++;
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        //t2.join();
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行了："+(endTime - starTime)+"毫秒");
    }
}
