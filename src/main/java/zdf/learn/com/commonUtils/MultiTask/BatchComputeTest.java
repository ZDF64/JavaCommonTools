package zdf.learn.com.commonUtils.MultiTask;

import zdf.learn.com.commonUtils.MultiTask.inf.BatchComputer;
import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanSqlDel;
import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanSqlInsert;

public class BatchComputeTest {
	public static void main(String[] ars) throws InterruptedException {
		int a = 1<<5;
		System.out.println(a);
//		0000 0000 0000 0000 0000 0001
//		0000 0000 0000 0000 0000 0010
//		0000 0000 0000 0000 0000 0100
//		0000 0000 0000 0000 0000 1000
		BatchComputer batchCom = new BatchComputer(12,6);
		new Thread(()->{
			for(int i = 0 ; i < 64 ; i ++ ) {
				batchCom.addTask(new TaskBeanSqlDel(null, i+""));
			}
		}).start();
		
		new Thread(()->{
			for(int i = 128 ; i < 256 ; i ++ ) {
				batchCom.addTask(new TaskBeanSqlInsert(null, i+""));
			}
		}).start();
		Thread.sleep(5000);
		System.out.println("Size:"+batchCom.selectRemainedTask().size());;
		
		
		batchCom.startCompute();
	}
}
