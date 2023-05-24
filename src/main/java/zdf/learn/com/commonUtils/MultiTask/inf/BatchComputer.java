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

/**
 * 多线程任务分配核心
 * @Project       CommonUtils
 * @CreatedTime   2023年5月4日
 * @Content       
 * @author        ZDF64
 *
 */
public class BatchComputer implements BatchComputeImpl{
	private boolean cancelled;
	public int BatchSize;
	public int PartitionSize; 
	public int waitMax = 3;
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
		cancelled = false;
		int k =0;
		List<TaskBean> innerTask = new ArrayList<TaskBean>();
		while(!cancelled) {
			if(waitingForComputeQuere.peek()!=null) {
				TaskBean task =  waitingForComputeQuere.poll();
				innerTask.add(task);
				if(innerTask.size()>=PartitionSize) {
					TaskMultiSlot slot = new TaskMultiSlot(innerTask,PartitionSize);
					multiPool.invoke(slot);
					innerTask = new ArrayList<TaskBean>();
				}
			}
			try {
				Thread.sleep(1000);
				if(waitingForComputeQuere.peek() ==null) {
					k++;
					System.out.println("waiting:"+k);
				}else {
					k=0;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(k>waitMax) {
				cancelled = true;
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
		this.cancelled = true;
		multiPool.shutdown();
	}

}
