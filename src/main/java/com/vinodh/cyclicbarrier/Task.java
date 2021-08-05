package com.vinodh.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task extends Thread {
    
	private int delay;
	private CyclicBarrier barrier;

    public Task(int delay, CyclicBarrier barrier, String name) {
    	super(name);
		this.delay = delay;
    	this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            add();
            barrier.await();
            System.out.println(Thread.currentThread().isAlive() + " has crossed the barrier");
        } catch (InterruptedException ex) {
        	ex.printStackTrace();
        } catch (BrokenBarrierException ex) {
        	ex.printStackTrace();
        }
    }
    private int add() throws InterruptedException {
		int a = 10;
		int b = 20;
		int c = a + b;
		Thread.sleep(delay);
        System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
		return c;
	}

}
