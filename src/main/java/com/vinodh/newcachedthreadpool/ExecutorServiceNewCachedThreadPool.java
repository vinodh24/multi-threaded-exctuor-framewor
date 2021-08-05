package com.vinodh.newcachedthreadpool;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceNewCachedThreadPool {
	static ExecutorService service = null;
	static Set<Callable<Map<String, Integer>>> callable = null;

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		callable = new HashSet<Callable<Map<String, Integer>>>();
		// creates cached thread pool
		service = Executors.newCachedThreadPool();
		TaskCallable task = new TaskCallable(10, 20, "vinodh");
		callable.add(task);
		TaskCallable task1 = new TaskCallable(15, 25, "abhi");
		callable.add(task1);
		try {
			List<Future<Map<String, Integer>>> future = service.invokeAll(callable);
			for (Future<Map<String, Integer>> futureObj : future) {
				Map<String, Integer> result = futureObj.get();
				System.out.println("result " + result);
			}
			boolean isTerminated = service.isTerminated();
			System.out.println("isTerminated = " + isTerminated);
			
			TaskRunnable taskRunnable = new TaskRunnable(40, 20, "lahari");
			TaskRunnable taskRunnable1 = new TaskRunnable(50, 25, "Navya");
			//because of we are not storing values to callable so thats why we are getting null
			Future<?> taskRunnablevalue=service.submit(taskRunnable);
			System.out.println("taskRunnablevalue  ::: "+taskRunnablevalue.get());
			service.submit(taskRunnable1);
		
			boolean isTerminated1 = service.isTerminated();
			System.out.println("isTerminated = " + isTerminated1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}

	}
}
