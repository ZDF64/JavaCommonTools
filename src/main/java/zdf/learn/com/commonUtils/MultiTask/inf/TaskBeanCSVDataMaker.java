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
	private String fileName ="data";
	public TaskBeanCSVDataMaker(String baseName,String fileName){
		this.baseName = baseName;
		this.fileName = fileName;
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
//				+",\""+utils.makeHexStr.get()+"\""
				+",\""+utils.createDateStringByFormat.apply("yyyyMMddHHmmss")+"\""
//				+",\""+utils.createDateAfterNowTime.get().toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+"\"";
				+",\""+ utils.makeSettingId.get() +"\""
				+",\""+ utils.makeSettingValue.get() +"\"";
		DfTool.toWrite(logString, "E:\\console\\can-a\\ma\\"+baseName+"\\"+fileName+".csv", true);
		return null;
	}
	
}
