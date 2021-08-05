package com.vinodh.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author vinodh.sangavaram
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		// Let us create task that is going to
		// wait for four threads before it starts
		CountDownLatch latch = new CountDownLatch(4);

		// Let us create four worker
		// threads and start them.
		Worker first = new Worker(1000, latch, "WORKER-1");
		Worker second = new Worker(2000, latch, "WORKER-2");
		Worker third = new Worker(3000, latch, "WORKER-3");
		Worker fourth = new Worker(4000, latch, "WORKER-4");

		first.start();
		System.out.println(first.getName() + " is isAlive " + first.isAlive());
		second.start();
		third.start();
		fourth.start();

		// The main task waits for four threads
		latch.await();
		System.out.println(first.getName() + " is isAlive = " + first.isAlive());
		// Main thread has started
		System.out.println(Thread.currentThread().getName() + " has finished");

	}

}
