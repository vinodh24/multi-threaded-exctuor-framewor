package com.vinodh.scheduledthreadPoolexecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolRunnable {
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		TaskRunnable task1 = new TaskRunnable(10, 20, "Demo Task 1");
		TaskRunnable task2 = new TaskRunnable(20, 30, "Demo Task 2");

		System.out.println("The time is : " + new Date());
		executor.schedule(task1, 5, TimeUnit.SECONDS);
		executor.schedule(task2, 10, TimeUnit.SECONDS);
		try {
			executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
		} finally {
			if (executor != null) {
				System.out.println("before block : " +" - Time - " + new Date());
				executor.shutdown();
				System.out.println("after block : " +" - Time - " + new Date());
			}
		}
		
	}
	
	
}
