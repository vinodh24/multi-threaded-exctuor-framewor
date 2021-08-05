package com.vinodh.scheduledthreadPoolexecutor;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorCallable {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		Task task1 = new Task(10, 20, "Demo Task 1");
		Task task2 = new Task(20, 30, "Demo Task 2");

		System.out.println("The time is : " + new Date());
        
		FutureTask<Map<String, Integer>> future=(FutureTask<Map<String, Integer>>) executor.schedule(task1, 5, TimeUnit.SECONDS);
		FutureTask<Map<String, Integer>> future1=(FutureTask<Map<String, Integer>>) executor.schedule(task2, 10, TimeUnit.SECONDS);

		
		System.out.println("future one ::: "+future.get());
		System.out.println(" future two ::: "+future1.get());
		
		
		//it wont get the Threads output if we go this methods
		/*executor.schedule(task1, 5, TimeUnit.SECONDS);
		executor.schedule(task2, 10, TimeUnit.SECONDS);*/

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
