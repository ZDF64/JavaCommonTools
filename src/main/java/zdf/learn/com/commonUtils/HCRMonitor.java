package zdf.learn.com.commonUtils;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;

public class HCRMonitor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefangFileHandle deTool = new DefangFileHandle();
		DefangFileHandle.fileNumberCount("E:\\console\\HCR\\csv\\T", "TMCI");
		DefangFileHandle.fileNumberCount("E:\\console\\HCR\\csv\\G", "GTMC");
		DefangFileHandle.fileNumberCount("E:\\console\\HCR\\csv\\F", "FTMS");
	}

}
