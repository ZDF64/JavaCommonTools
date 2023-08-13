package zdf.learn.com.commonUtils.MultiTask.inf;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import zdf.learn.com.commonUtils.DataBase.JdbcEnegine;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;

public class TaskBeanInsertDbFromCSV implements TaskBean, Closeable {

	private String sql = "select 1";
	private Integer max = 10000;
	public TaskBeanInsertDbFromCSV(String sql) {
		this.sql = sql;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <V, T> V compute(Consumer<T> callback) {
		// TODO Auto-generated method stub
//		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://tmcihcrproddbquery01.mysql.database.chinacloudapi.cn:3306/bjhcrdb?useUnicode=true&characterEncoding=utf8&useSSL=true",
//				"apuser@tmcihcrproddbquery01", 
//				"KtEV2dErT29eC3tx");
//		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://172.31.64.114:3306/bjhcrdb?useUnicode=true&characterEncoding=utf8&useSSL=true",
//						"root", 
//						"3bQqPo4kEL");
		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://172.31.63.131:3306/gzhcrdb?useUnicode=true&characterEncoding=utf8&useSSL=true",
				"root", 
				"XkFhifQe7M");
		int rs = jdbc.execInsert(sql);
//		
//		return (V) (rs+"");
		
//		System.out.println("hash:::"+this.hashCode());
//		try {
//			Thread.sleep(3000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		return null;
	}

}
