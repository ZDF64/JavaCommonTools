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

public class TaskBeanFetchDbToCSV implements TaskBean, Closeable {

	private String sql = "select 1";
	private String bakUrl = "";
	private String tableName = "";
	private Integer offset = 1;
	private Integer max = 10000;
	private List<String> columns = null;
	public TaskBeanFetchDbToCSV(String sql,String bakUrl,int offset,int max) {
		this.sql = sql;
		this.bakUrl = bakUrl;
		this.offset = offset;
		this.max = max;
		this.columns = Arrays.asList(sql.substring(sql.indexOf("select")+6, sql.indexOf("from")).split(",")) ;
		this.tableName = sql.substring(sql.indexOf("from")+4, sql.indexOf("where")<0?sql.length()-1:sql.indexOf("where"));
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
		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://tmcihcrproddbquery02.mysql.database.chinacloudapi.cn:3306/gzhcrdb?useUnicode=true&characterEncoding=utf8&useSSL=true",
				"apuser@tmcihcrproddbquery02", 
				"KtEV2dErT29eC3tx");
		ResultSet rs = jdbc.exec(sql + " limit "+offset+","+max);
		DefangFileHandle dfTool = new DefangFileHandle();
		String[] aggYearMonthArray = new String[] {"202207","202208","202209","202210","202211","202212","202301","202302","202303"};
		try {
			while(rs.next()) {
				
				for(String aggYearMonth : aggYearMonthArray) {
					String line = "insert into " + tableName +" VALUES ( NULL ,";  
					for(int i = 2 ; i<= columns.size() ; i ++) {
						if(i ==3) {
							line =  line + "'"+aggYearMonth+"',";
						}else {
							line =  line + "'"+rs.getString(i) +"',";
						}
					}
					line = line.substring(0, line.lastIndexOf(","));
					line = line +")";
					dfTool.toWrite(line, bakUrl, true);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
