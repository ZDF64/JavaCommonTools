package zdf.learn.com.commonUtils.MultiTask.inf;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;

public class TaskBeanSqlDel implements TaskBean, Closeable {

	private Connection con;
	private String sql ;
	public TaskBeanSqlDel(Connection con,String sql) {
		this.con = con;
		this.sql = sql;
	}
	@Override
	public <V, T> V compute(Consumer<T> callback) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			System.out.println("compute SqlDel:"+sql);
//			PreparedStatement ps =con.prepareStatement(sql);
//			ps.execute();
//			callback.andThen(c->{
//				try {
//					ps.close();
//					close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}).accept((T) sql);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		return null;
	}
	@Override
	public void close() throws IOException {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
