package zdf.learn.com.commonUtils.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcEnegine {

	Connection con;
	public JdbcEnegine(String url, String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int execInsert(String sql) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			return ps.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
	public ResultSet exec(String sql) {
		try {
			System.out.println(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			return ps.executeQuery();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}
	/**
	 *  执行更新操作
	 * @param sql
	 * @param params 每个Map只放一对儿条件, 格式 key = value
	 */
	public void execUpdate(String sql,List<List<Map<String, String>>> params) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for(List<Map<String, String>> param : params) {
				for(int i = 1 ; i < param.size() ; i++) {
					Map<String, String> innerMap = param.get(i);
					String key = innerMap.keySet().stream().collect(Collectors.toList()).get(0);
					try {
						ps.setString(i, key + " = '" +innerMap.get(key) +"'");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				ps.addBatch();
			}
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[] execInsert(String sql, List<List<Map<String, String>>> params) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			params.forEach(paramsList -> {
				try {
					Integer index = 1 ; 
					for(Map<String, String> param : paramsList) {
//						System.out.println(index+":::::"+param.values().iterator().next());
						ps.setString(index, param.values().iterator().next());
						index ++;
					}
					ps.addBatch();
				} catch (Exception e) {
					System.err.println(e.getMessage());

				}
			});
			return ps.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			return new int[] {};
		}
	}

	/**
	 * 删除
	 * 
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public int execDel(String sql) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			return ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param <M>
	 * @param <R>
	 * @param <O>
	 * @param <U>
	 * @param <T>
	 * @return
	 * @return
	 * 
	 */
	public <T> List<T> execFetch(String sql, Function<ResultSet, List<T>> parser) {
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			return parser.apply(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<String> execTargetFetch(String sql) {
		try {
			List<String> rsList = new ArrayList<>();
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("fin=====");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rsList.add(rs.getString(1));
			}
			return rsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void close() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	public static void main(String[] args) {
		JdbcEnegine jdbc = new JdbcEnegine("jdbc:mysql://172.31.64.114:3306/bjhcrdb", "root", "3bQqPo4kEL");
		try {
			ResultSet rs = jdbc.exec("SELECT battery_vlotage_inf_id, vehicle_id, lowest_vlotage, regist_date, update_date FROM bjhcrdb.battery_vlotage_inf;");
			while (rs.next()) {
				System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
				System.out.println(rs.getObject(3));
				System.out.println(rs.getObject(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
