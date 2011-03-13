package com.google.educloud.cloudnode.scheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.google.educloud.cloudnode.scheduler.tasks.NodeTask;

public class Scheduler implements Runnable {

	private static Logger LOG = Logger.getLogger(Scheduler.class);

	private static ArrayBlockingQueue<NodeTask> queue = new ArrayBlockingQueue<NodeTask>(300);

	private ExecutorService es;

	public Scheduler() {
		es = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		while (true) {
			try {
				consume(queue.take());
			} catch (InterruptedException e) {
				LOG.error("Scheduler was interrupted", e);
			}
		}
	}

	private void consume(NodeTask task) {
		LOG.debug("Scheduler will consume a new task");

		es.execute(task);
	}

	public void addTask(NodeTask task) {
		queue.add(task);
	}


}
