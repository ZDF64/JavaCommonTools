package zdf.learn.com.commonUtils.data;

import zdf.learn.com.commonUtils.MultiTask.inf.BatchComputer;
import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanCSVDataMaker;

public class MakeCSVData {
	public static void main(String[] args) throws InterruptedException {
		BatchComputer batchCom = new BatchComputer(12,6);
		new Thread(()->{
			for(int i = 0 ; i < 70000 ; i ++ ) {
				batchCom.addTask(new TaskBeanCSVDataMaker("TMCI"));
			}
		}).start();
		new Thread(()->{
			for(int i = 0 ; i < 190000 ; i ++ ) {
				batchCom.addTask(new TaskBeanCSVDataMaker("FTMS"));
			}
		}).start();
		new Thread(()->{
			for(int i = 0 ; i < 250000 ; i ++ ) {
				batchCom.addTask(new TaskBeanCSVDataMaker("GTMC"));
			}
		}).start();
		batchCom.startCompute();
	}
}
