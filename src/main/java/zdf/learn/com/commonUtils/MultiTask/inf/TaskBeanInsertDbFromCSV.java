package zdf.learn.com.commonUtils.MultiTask.inf;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import zdf.learn.com.commonUtils.DataBase.JdbcEnegine;
import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;

public class TaskBeanInsertDbFromCSV implements TaskBean, Closeable {

	private List<String> sqls = new ArrayList<>();
	private Integer max = 10000;
	public TaskBeanInsertDbFromCSV(List<String> sqls) {
		this.sqls = sqls;
	}
	@Override
	public void close() throws IOException {

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
		for(String sql : sqls) {
			jdbc.execInsert(sql);
		}
		System.out.println(Thread.currentThread().getName()+" sqls insert fin:::"+sqls.size());
		try {
			jdbc.close();
			System.out.println("jdbc closed");
		} catch (Exception e) {
			System.out.println("close error:"+e.getMessage());
		}
		return (V) (sqls.size()+"");
	}

}
