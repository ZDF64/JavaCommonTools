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
		new Thread(()->{
			this.exec();
		}).start();
		
	}
	
	public void exec() {
		List<TaskBean> innerTask = new ArrayList<TaskBean>();
		int emptyRunning = 0;
		while(!cancelled) {
			if(waitingForComputeQuere.peek()!=null) {
				
				TaskBean task =  waitingForComputeQuere.poll();
				innerTask.add(task);
				if(innerTask.size()>=PartitionSize) {
					batchMultiRunner(innerTask);
					emptyRunning = 0;
				}
				emptyRunning ++;
				if(emptyRunning>100) {
					batchMultiRunner(innerTask);
					emptyRunning = 0;
				}
			}
		}
		if(innerTask.size()>0) {
			TaskMultiSlot slot = new TaskMultiSlot(innerTask,PartitionSize);
			multiPool.invoke(slot);
			innerTask.clear();
		}
		System.out.println("Finished");
	}
	
	private void batchMultiRunner(List<TaskBean> innerTask) {
		TaskMultiSlot slot = new TaskMultiSlot(innerTask,PartitionSize);
		multiPool.invoke(slot);
		//innerTask = new ArrayList<TaskBean>(); // 这里不能用 new的方式清空，会导致父方法的数据依旧存在
		innerTask.clear();
		System.out.println("Running.....");
		System.out.println("活跃线程数："+multiPool.getActiveThreadCount());
		System.out.println("并行数："+multiPool.getParallelism());
		System.out.println("窃取任务数："+multiPool.getStealCount());
	}
	
	@Override
	public void stopCompute() {
		// TODO Auto-generated method stub
		this.cancelled = true;
		multiPool.shutdown();
	}

}
