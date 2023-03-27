package zdf.learn.com.commonUtils.MultiTask.inf;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.function.Consumer;

import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;

public class TaskBeanSqlInsert implements TaskBean, Closeable{
	
	private Connection con;
	private String sql ;
	public TaskBeanSqlInsert(Connection con,String sql) {
		this.con = con;
		this.sql = sql;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <V, T> V compute(Consumer<T> callback) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("compute SqlInsert:"+sql);
		return null;
	}

}
