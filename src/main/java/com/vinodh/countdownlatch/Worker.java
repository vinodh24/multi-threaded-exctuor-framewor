package com.vinodh.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {
	private int delay;
	private CountDownLatch latch;

	public Worker(int delay, CountDownLatch latch, String name) {
		super(name);
		this.delay = delay;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			add();
			System.out.println("latch finish count" + latch.getCount());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int add() throws InterruptedException {
		int a = 10;
		int b = 20;
		int c = a + b;
		System.out.println(Thread.currentThread().getName() + " finished"
				+ " value" + c);
		Thread.sleep(delay);
		latch.countDown();
		return c;

	}
}
