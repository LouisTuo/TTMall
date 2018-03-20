package com.ttmall.pagehelper;

public class TestReflect {
	public static void main(String[] args) {
		try {
			Class cla = Class.forName("com.ttmall.pagehelper.TestNull");
			Object obj = cla.newInstance();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
