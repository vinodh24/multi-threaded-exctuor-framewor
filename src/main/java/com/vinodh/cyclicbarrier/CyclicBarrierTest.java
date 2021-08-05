package com.vinodh.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author vinodh.sangavaram
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		CyclicBarrier cb = new CyclicBarrier(5);
		Task first = new Task(1000, cb, "WORKER-1");
		Task second = new Task(2000, cb, "WORKER-2");
		Task third = new Task(3000, cb, "WORKER-3");
		Task fourth = new Task(4000, cb, "WORKER-4");
		
		
		first.start();
		second.start();
		third.start();
		fourth.start();
		
		cb.await();
		System.out.println(first.isAlive());
	}
}
