package com.hs.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyApplet extends Applet {

	@Override
	public void init() {
		System.out.println("init....");
		InputStream in = MyApplet.class.getResourceAsStream("config.properties");
		
		Properties prop = new Properties();
		try {
			prop.load(in);
			System.out.println(prop.get("test"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint (Graphics g){
      g.drawString ("Hello World", 25, 50);
   }
	
	
}
