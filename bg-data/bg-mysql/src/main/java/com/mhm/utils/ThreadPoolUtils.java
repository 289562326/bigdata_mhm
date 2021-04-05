package com.mhm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 *
 * @author Administrator
 */
public class ThreadPoolUtils {
	public Logger log = LoggerFactory.getLogger(ThreadPoolUtils.class);
	private static ExecutorService executor = Executors.newFixedThreadPool(10, new ThreadFactory() {
		private AtomicInteger count = new AtomicInteger();

		public Thread newThread(Runnable r) {
			int c = count.incrementAndGet();
			Thread t = new Thread(r);
			t.setName("Common-pool-Thread-" + c);
			t.setUncaughtExceptionHandler((t1, e) -> System.out.println("s:"+e));
			return t;
		}
	});

	public static void exec(Runnable run) {

		if (executor.isShutdown()) {
			return;
		}

		executor.execute(run);
	}

	public static void shutDown() {
		executor.shutdown();
	}
}
