package com.vinodh.executorservice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Task implements Callable<Map<String,Integer>>{
	private int a;
	private int b;
	private String name;
	public Task(int a,int b,String name){
		this.a=a;
		this.b=b;
		this.name=name;
	}
	private Map<String, Integer> add(int a,int b,String name) throws InterruptedException {
		int c = a + b;
		System.out.println(Thread.currentThread().getName() + " finished"+ " value" + c);
		Map<String, Integer> addResult=new HashMap<String, Integer>();
		addResult.put(name, c);
		return addResult;

	}

	@Override
	public Map<String, Integer> call() throws Exception {
		return add(a, b,name);
	}
}
