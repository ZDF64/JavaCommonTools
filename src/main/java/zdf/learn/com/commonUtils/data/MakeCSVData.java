package zdf.learn.com.commonUtils.data;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.MultiTask.inf.BatchComputer;
import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanCSVDataMaker;

public class MakeCSVData {
	public static void main(String[] args) throws InterruptedException {
		BatchComputer batchCom = new BatchComputer(112,10);
		
		new Thread(()->{
			for(int k = 0 ; k < 2 ; k ++ ) {
				DefangFileHandle DfTool = new DefangFileHandle();
				DfTool.toWrite("vin,code,monitoringtime,settingid,settingvalue", "E:\\console\\can-a\\ma\\TMCI\\data"+k+".csv", true);
				for(int i = 0 ; i < 200000 ; i ++ ) {
					batchCom.addTask(new TaskBeanCSVDataMaker("TMCI","data"+k));
				}
			}
		}).start();
		new Thread(()->{
			for(int k = 0 ; k < 6 ; k ++ ) {
				DefangFileHandle DfTool = new DefangFileHandle();
				DfTool.toWrite("vin,code,monitoringtime,settingid,settingvalue", "E:\\console\\can-a\\ma\\FTMS\\data"+k+".csv", true);
				for(int i = 0 ; i < 200000 ; i ++ ) {
					batchCom.addTask(new TaskBeanCSVDataMaker("FTMS","data"+k));
				}
			}
		}).start();
		new Thread(()->{
			for(int k = 0 ; k < 7 ; k ++ ) {
				DefangFileHandle DfTool = new DefangFileHandle();
				DfTool.toWrite("vin,code,monitoringtime,settingid,settingvalue", "E:\\console\\can-a\\ma\\GTMC\\data"+k+".csv", true);
				for(int i = 0 ; i < 200000 ; i ++ ) {
					batchCom.addTask(new TaskBeanCSVDataMaker("GTMC","data"+k));
				}
			}
			
		}).start();
		batchCom.startCompute();
	}
}
