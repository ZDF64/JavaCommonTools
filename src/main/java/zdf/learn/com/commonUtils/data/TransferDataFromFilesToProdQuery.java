package zdf.learn.com.commonUtils.data;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import zdf.learn.com.commonUtils.DataBase.JdbcEnegine;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.MultiTask.inf.BatchComputer;
import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanInsertDbFromCSV;


public class TransferDataFromFilesToProdQuery {
	List<String> sqls = new ArrayList<>();
	public void insertToProd() {
		BatchComputer batchCom = new BatchComputer(12,6);
		batchCom.startCompute();
		File allFiles = new File("E:\\console\\allData");
		
		if(allFiles.isDirectory()) {
			File[] fileArray = allFiles.listFiles();
			DefangFileHandle dfTool = new DefangFileHandle();
			for(File fs : fileArray) {
				System.out.println(fs.getName());
				dfTool.readToLine(fs,sql->{
					sqls.add(sql);
					if(sqls.size()>20) {
						TaskBeanInsertDbFromCSV task = new TaskBeanInsertDbFromCSV(sqls);
						batchCom.addTask(task);
						sqls.clear();
					}
				});
				if(sqls.size()>0) {
					TaskBeanInsertDbFromCSV task = new TaskBeanInsertDbFromCSV(sqls);
					batchCom.addTask(task);
					sqls = new ArrayList<>();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
//		TransferDataFromFilesToProdQuery tps = new TransferDataFromFilesToProdQuery();
//		tps.insertToProd();
		
//		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://172.31.64.114:3306/bjhcrdb?useUnicode=true&characterEncoding=utf8&useSSL=true",
//				"root", 
//				"3bQqPo4kEL");
		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://172.31.63.131:3306/gzhcrdb","root","XkFhifQe7M");
		ResultSet rs = jdbc.exec("select * from air_conditioning_use_time_inf");
		try {
			while (rs.next()) {
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
