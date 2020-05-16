package com.hs.dubbo.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application.xml"});
        context.start();
        System.out.println("Provider register Success");
        try {
            System.in.read();//让此程序一直跑，表示一直提供服务
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
