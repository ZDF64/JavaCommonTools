package zdf.learn.com.commonUtils.MultiTask.inf;

import java.io.Closeable;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.function.Consumer;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;
import zdf.learn.com.commonUtils.tools.DataMakeUtils;

public class TaskBeanCSVDataMaker implements TaskBean, Closeable {

	private String baseName ="";
	public TaskBeanCSVDataMaker(String baseName){
		this.baseName = baseName;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * vin,code,tripcount,igon_time,igoff_time
	 */
	@Override
	public <V, T> V compute(Consumer<T> callback) {
		// TODO Auto-generated method stub
		DefangFileHandle DfTool = new DefangFileHandle();
		DataMakeUtils utils = new DataMakeUtils();
		String logString = "\""+utils.makeVin.get()+"\""
				+",\""+baseName+"\""
				+",\""+utils.makeHexStr.get()+"\""
				+",\""+utils.createDateTime.get().toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+"\""
				+",\""+utils.createDateAfterNowTime.get().toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+"\"";
		DfTool.toWrite(logString, "E:\\console\\can-a\\data\\"+baseName+"\\data.csv", true);
		return null;
	}
	
}
