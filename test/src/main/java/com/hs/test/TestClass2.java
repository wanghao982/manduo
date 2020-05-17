package com.hs.test;

import flexjson.JSONSerializer;

public class TestClass2 {

	public static void main(String[] args) {
		JSONSerializer json = new JSONSerializer();
		System.out.println(json.serialize("<M>"));
		
		System.err.println("VVV");
		
		System.out.println("MMM");
	}
}
