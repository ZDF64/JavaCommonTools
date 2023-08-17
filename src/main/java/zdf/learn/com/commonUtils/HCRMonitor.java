package zdf.learn.com.commonUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;

public class HCRMonitor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DefangFileHandle deTool = new DefangFileHandle();
//		DefangFileHandle.fileNumberCount("E:\\console\\HCR\\csv\\T", "TMCI");
//		DefangFileHandle.fileNumberCount("E:\\console\\HCR\\csv\\G", "GTMC");
//		DefangFileHandle.fileNumberCount("E:\\console\\HCR\\csv\\F", "FTMS");
		
		DefangFileHandle dfTools = new DefangFileHandle();
		Set<String> vinSet = new HashSet<String>();
		dfTools.readToLine(new File("C:\\Users\\ZDF64\\Desktop\\stderr_AWS.txt")).stream()
		.filter(f->{
			return f.contains("S3")||f.contains("s3");
		}).forEach(f->{
			System.out.println(f);
			dfTools.toWrite(f, "C:\\Users\\ZDF64\\Desktop\\result_s3.txt", true);
		});;
		
		
		
//		DefangFileHandle.fileNumberCount("D:\\home\\apuser\\datamake\\all", "TMCI");
//		deTool.distinctToNew("D:\\home\\apuser\\data\\vinList.csv", "D:\\home\\apuser\\data\\vinDistList.csv");
	}

}
