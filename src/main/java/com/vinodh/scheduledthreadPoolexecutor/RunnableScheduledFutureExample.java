package com.vinodh.scheduledthreadPoolexecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RunnableScheduledFutureExample {
	  public static void main(String[] args) throws ExecutionException, InterruptedException {
	      Callable<Long> callable = new Callable<Long>() {
	          @Override
	          public Long call() throws Exception {
	              return ThreadLocalRandom.current().nextLong();
	          }
	      };

	      ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
	      RunnableScheduledFuture<Long> scheduleFuture =
	              (RunnableScheduledFuture<Long>) es.schedule(callable, 2, TimeUnit.SECONDS);
	      System.out.println("remaining delay: " +scheduleFuture.getDelay(TimeUnit.MILLISECONDS));
	      System.out.println("periodic: " +scheduleFuture.isPeriodic());
	      Long result = scheduleFuture.get();
	      System.out.println(result);
	      es.shutdown();
	  }
	}
