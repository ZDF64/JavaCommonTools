package zdf.learn.com.commonUtils.Files;

import java.io.File;

public class FilesTool {
	
	public static void main(String[] args) {
		FilesTool ftools = new FilesTool();
		ftools.findAllFilesByType("E:\\console\\can-a\\data\\outside", ".parquet", "E:\\console\\can-a\\data\\outside\\new_fils.log");
	}
	
	
	public void findAllFilesByType(String rootUrl,String typeStr,String outPutFileUrl) {
		
		try {
			DefangFileHandle DfTools = new DefangFileHandle();
			File fs = new File(rootUrl);
			if(fs.isDirectory()) {
				File[] childFs = fs.listFiles();
				for(File fsNext : childFs) {
					if(fsNext.isDirectory()) {
						findAllFilesByType(fsNext.getPath(), typeStr,outPutFileUrl);
					}else {
						if(fsNext.getName().endsWith(typeStr)) {
							DfTools.toWrite(fsNext.getPath()+","+fsNext.getName(), outPutFileUrl, true);
						}
					}
				}
				
			}else {
				DfTools.toWrite(fs.getPath()+","+fs.getName(), outPutFileUrl, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
