package zdf.learn.com.commonUtils.MultiTask.inf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;

import zdf.learn.com.commonUtils.MultiTask.impl.BatchComputeImpl;
import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;
import zdf.learn.com.commonUtils.MultiTask.impl.TaskMultiSlot;

public class BatchComputer implements BatchComputeImpl{
	public int BatchSize;
	public int PartitionSize; 
	ConcurrentLinkedQueue<TaskBean> waitingForComputeQuere = new ConcurrentLinkedQueue<TaskBean>();
	ConcurrentLinkedQueue<TaskBean> FinishedQuere = new ConcurrentLinkedQueue<TaskBean>();
	ForkJoinPool multiPool = null;
	/**
	 * @param BatchSize       最大并行度
	 * @param PartitionSize   最大队列深度
	 */
	public BatchComputer(int BatchSize,int PartitionSize) {
		this.BatchSize = BatchSize;
		this.PartitionSize = PartitionSize;
		waitingForComputeQuere = new ConcurrentLinkedQueue<TaskBean>();
		multiPool = new ForkJoinPool(BatchSize);
	}
	@Override
	public int addTask(TaskBean task) {
		waitingForComputeQuere.offer(task);
		return waitingForComputeQuere.size();
	}

	@Override
	public ConcurrentLinkedQueue<TaskBean> selectRemainedTask() {
		return waitingForComputeQuere;
	}

	@Override
	public int producer(TaskBean pro,InvocationHandler proxyHandle) {
		TaskBean proxyTask = (TaskBean) Proxy.newProxyInstance(pro.getClass().getClassLoader() // 类加载器
								                , new Class<?>[] { TaskBean.class } // 继承的接口们
								                , proxyHandle);
		waitingForComputeQuere.add(proxyTask);
		return waitingForComputeQuere.size();
	}

	@Override
	public int consumer(TaskBean con) {
		// TODO Auto-generated method stub
		con.compute(f->{
			FinishedQuere.offer(con);
		});
		return FinishedQuere.size();
	}

	@Override
	public void startCompute() {
		List<TaskBean> innerTask = new ArrayList<TaskBean>();
		while(waitingForComputeQuere.peek()!=null) {
			TaskBean task =  waitingForComputeQuere.poll();
			innerTask.add(task);
			if(innerTask.size()>=PartitionSize) {
				TaskMultiSlot slot = new TaskMultiSlot(innerTask,PartitionSize);
				multiPool.invoke(slot);
				innerTask = new ArrayList<TaskBean>();
			}
		}
		if(innerTask.size()>0) {
			TaskMultiSlot slot = new TaskMultiSlot(innerTask,PartitionSize);
			multiPool.invoke(slot);
			innerTask = new ArrayList<TaskBean>();
		}
		System.out.println("Finished");
		
	}
	@Override
	public void stopCompute() {
		// TODO Auto-generated method stub
		
		multiPool.shutdown();
	}

}
