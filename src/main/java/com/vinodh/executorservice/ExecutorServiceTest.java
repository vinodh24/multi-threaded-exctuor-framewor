package com.vinodh.executorservice;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
	
	public static void main(String[] args) throws ExecutionException {
		ExecutorService service = null;
		Set<Callable<Map<String, Integer>>> callable = null;
		service = Executors.newFixedThreadPool(2);
		callable = new HashSet<Callable<Map<String,Integer>>>();
		Task task = new Task(10, 20,"vinodh");
		callable.add(task);
		Task task1 = new Task(15, 25,"abhi");
		callable.add(task1);
		Task task2 = new Task(20, 30,"praveen");
		callable.add(task2);
		Task task3 = new Task(25, 35,"kumar");
		callable.add(task3);
		try {
			List<Future<Map<String, Integer>>> future =service.invokeAll(callable);
			for (Future<Map<String,Integer>> futureObj : future) {
				Map<String,Integer> result=futureObj.get();
				System.out.println("result "+result);
				}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(service!=null){
				service.shutdown();
			}
		}
		
	}
	
}
