package com.sos;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: Test2
 * @Description:
 * @date 2018年11月23日
 *
 */

public class Test2 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程开始");
		final CountDownLatch latch = new CountDownLatch(3);
		ExecutorService pool = Executors.newFixedThreadPool(3);
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(50);
					System.out.println("子线程1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					latch.countDown();
				}
				
				
			}
		});
		
		pool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("子线程2");
				latch.countDown();
			}
		});
		
		pool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("子线程3");
				latch.countDown();
			}
		});
		
		latch.await();
		pool.shutdown();
		System.out.println("主线程结束");
		System.exit(0);
	}

}
