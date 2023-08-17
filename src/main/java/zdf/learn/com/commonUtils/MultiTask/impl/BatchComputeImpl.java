package zdf.learn.com.commonUtils.MultiTask.impl;

import java.lang.reflect.InvocationHandler;
import java.util.concurrent.ConcurrentLinkedQueue;

import zdf.learn.com.commonUtils.MultiTask.inf.TaskBean;

public interface BatchComputeImpl {

	
	public int addTask(TaskBean task);
	public ConcurrentLinkedQueue<TaskBean> selectRemainedTask();
	public int producer(TaskBean pro,InvocationHandler proxyHandle);
	public int consumer(TaskBean con);
	public void startCompute();
	public void stopCompute();
}
