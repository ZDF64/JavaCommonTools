package zdf.learn.com.commonUtils.MultiTask.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import zdf.learn.com.commonUtils.MultiTask.Proxy.TaskMultiInvocationHandler;

public class TaskMultiSlot extends RecursiveTask<TaskBean> {

	private static final long serialVersionUID = -2554187880901621L;
	private List<TaskBean> taskPool ;
	private int partitionSize;
	public TaskMultiSlot(List<TaskBean> task,int partitionSize) {
		this.taskPool = task;
		this.partitionSize = partitionSize;
	}
	

	@Override
	protected TaskBean compute() {
		// TODO Auto-generated method stub
		int blockSize = taskPool.size()/partitionSize;
		if(blockSize>1) {
			List<TaskBean> taskLeftInner = new ArrayList<>();
			List<TaskBean> taskRightInner = new ArrayList<>();
			for(int i = 0 ;i < taskPool.size() ; i ++) {
				TaskBean taskIn = taskPool.get(i);
				if(i%2==0) {
					taskLeftInner.add(taskIn);
				}else {
					taskRightInner.add(taskIn);
				}
			}
			TaskMultiSlot left = new TaskMultiSlot(taskLeftInner,partitionSize);
			TaskMultiSlot right = new TaskMultiSlot(taskRightInner,partitionSize);
			left.fork();
			right.fork();
		}else{
			for(TaskBean task : taskPool ) {
				TaskMultiInvocationHandler taskProxyHandler = new TaskMultiInvocationHandler(task);
				TaskBean taskProxy = (TaskBean) taskProxyHandler.getProxyInstance();
				taskProxy.compute(con->{
					
				});
			}
		}
		return null;
	}

}
