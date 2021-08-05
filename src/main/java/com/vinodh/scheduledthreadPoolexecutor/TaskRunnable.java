package com.vinodh.scheduledthreadPoolexecutor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskRunnable implements Runnable {
	private String name;
	private int a;
	private int b;

	public TaskRunnable(int a, int b, String name) {
		this.a = a;
		this.b = b;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Map<String, Integer> add() {
		int c = a + b;
		Map<String, Integer> addResult = new HashMap<String, Integer>();
		System.out.println("Doing a task during : " + name + " - Time - "
				+ new Date());
		addResult.put(name, c);
		System.out.println(addResult);
		return addResult;
	}

	@Override
	public void run() {
		add();
	}

}
