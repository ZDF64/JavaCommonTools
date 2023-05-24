package zdf.learn.com.commonUtils.DataBase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Column;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Id;

@Slf4j
public class JDBCTools {
	/**
	 * 将ResultSet解析成对应实体类
	 * @param <T>
	 * @param rs
	 * @param tableSchema
	 * @return
	 */
	public <T> List<T> parseResult(ResultSet rs, Class<T> tableSchema) {
		List<T> result = new ArrayList<>();
		try {
			while (rs.next()) {
				T t = tableSchema.newInstance();
				Column column = null;

				for (Field f : tableSchema.getDeclaredFields()) {
					/**
					 * 跳过静态和final
					 */
					if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
						continue;
					}
					f.setAccessible(true);
					column = f.getAnnotation(Column.class);
					if (column != null) {
						if (ZonedDateTime.class.equals(f.getType())) {

							Timestamp value = null;

							try {
								value = rs.getTimestamp(column.name().isEmpty() ? upperCharToUnderLine(f.getName()) : column.name());
							} catch (SQLException e) {
//		                            log.error("parseResult: ", e);
							}

							if (value != null) {
								f.set(t, value.toLocalDateTime().atZone(ZoneId.of("UTC+8")));
							}

						} else if (LocalDate.class.equals(f.getType())) {

							Timestamp value = null;

							try {
								value = rs.getTimestamp(column.name().isEmpty() ? upperCharToUnderLine(f.getName()) : column.name());
							} catch (SQLException e) {
//		                            log.error("parseResult: ", e);
							}

							if (value != null) {
								f.set(t, value.toLocalDateTime().atZone(ZoneId.of("UTC+8")).toLocalDate());
							}

						} else {

							try {
								f.set(t, rs.getObject(column.name().isEmpty() ? upperCharToUnderLine(f.getName()) : column.name()));
							} catch (SQLException e) {
//		                            log.error("parseResult: ", e);
							}
						}
					}else {
	                    
	                    if (ZonedDateTime.class.equals(f.getType())) {
	                        
	                        Timestamp value = null;
	                        
	                        try {
	                            value = rs.getTimestamp(upperCharToUnderLine(f.getName()));
	                        } catch (SQLException e) {
//	                            log.error("parseResult: ", e);
	                        }
	                        
	                        if (value != null) {
								f.set(t, value.toLocalDateTime().atZone(ZoneId.of("UTC+8")));
	                        }
	                    }else if (LocalDate.class.equals(f.getType())) {
	                        
	                        Timestamp value = null;
	                        
	                        try {
	                            value = rs.getTimestamp(upperCharToUnderLine(f.getName()));
	                        } catch (SQLException e) {
//	                            log.error("parseResult: ", e);
	                        }
	                        
	                        if (value != null) {
								f.set(t, value.toLocalDateTime().atZone(ZoneId.of("UTC+8")).toLocalDate());
	                        }
	                    }else {
	                        
	                        try {
	                            f.set(t, rs.getObject(upperCharToUnderLine(f.getName())));
	                        } catch (SQLException e) {
//	                            log.error("parseResult: ", e);
	                        }
	                    }
	                    
	                }

				}
				result.add(t);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param tableSchema 传入表对应的实体类
	 * @param operation   操作：insert,select,delete [todo] ,update [todo]
	 * @return
	 */
	public String parseToSql(Object tableEntity ,Class<?> tableSchema, String operation) {

		MysqlSchema mySchema = tableSchema.getAnnotation(MysqlSchema.class);
		if (mySchema == null) {
			log.error(tableSchema.getClass().getSimpleName() + " is not exists");
			return null;
		}
		
		/**
		 * 获取表名
		 */
		String tableName = mySchema.name().isEmpty() ? upperCharToUnderLine(tableSchema.getSimpleName())
				: mySchema.name();
		StringBuilder sb = new StringBuilder();
		Column column = null;
		Id isId = null;
		/**
		 * 通过反射获取属性
		 * 无值属性，适用于select delete
		 */
		if(operation.contains("select") || operation.contains("delete")  ) {
			for (Field f : mySchema.getClass().getDeclaredFields()) {
				/**
				 * 跳过静态和final的属性
				 */
				if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
					continue;
				}
				/**
				 * 抬升权限
				 */
				f.setAccessible(true);
				column = f.getAnnotation(Column.class);
				if (column != null) {
					sb.append(column.name().isEmpty() ? upperCharToUnderLine(f.getName()) : column.name());
					sb.append(",");
				} 
				else{
					sb.append(upperCharToUnderLine(f.getName()));
					sb.append(",");
				}

			}
		}
		
		/**
		 * 通过反射获取属性
		 * 有值属性，适用于update insert
		 */
		if(operation.contains("update") ) {
			for(Field f : tableEntity.getClass().getDeclaredFields()) {
				if(Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
					continue;
				}
				f.setAccessible(true);
				column = f.getAnnotation(Column.class);
				isId = f.getAnnotation(Id.class);
				try {
					if(isId != null){
						sb.append(isId.name().isEmpty() ? upperCharToUnderLine(f.getName()) : isId.name());
						sb.append("=");
						sb.append(f.get(tableEntity));
						sb.append(" <:::::> ");
					}else if(column != null ) {
						sb.append(column.name().equals("")||column.name().isEmpty() ? upperCharToUnderLine(f.getName()) : column.name());
						sb.append("=");
						if(String.class.equals(f.getType())) {
								sb.append(" '");
								sb.append(f.get(tableEntity));
								sb.append("' ");
						}else if(Integer.class.equals(f.getType()) 
									|| int.class.equals(f.getType()) 
									|| Double.class.equals(f.getType())
									|| double.class.equals(f.getType())
									|| Float.class.equals(f.getType())
									|| float.class.equals(f.getType())) {
							sb.append(f.get(tableEntity));
						}
						
						sb.append(",");
					}else {
						sb.append(upperCharToUnderLine(f.getName()));
						sb.append("=");
						if(String.class.equals(f.getType())) {
								sb.append(" '");
								sb.append(f.get(tableEntity));
								sb.append("' ");
						}else if(Integer.class.equals(f.getType()) 
									|| int.class.equals(f.getType()) 
									|| Double.class.equals(f.getType())
									|| double.class.equals(f.getType())
									|| Float.class.equals(f.getType())
									|| float.class.equals(f.getType())) {
							sb.append(f.get(tableEntity));
						}else if(LocalDate.class.equals(f.getType())){
							sb.append(" '");
							LocalDate now = (LocalDate) f.get(tableEntity);
							sb.append(now.toString());
							sb.append("' ");
						}
						sb.append(",");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(operation.contains("insert") ) {
			
		}
		return splicingSQL(operation, sb.substring(0, sb.lastIndexOf(",")), tableName);
	}

	/**
	 * 字符串首字符大写转下划线
	 * 
	 * @param param
	 * @return
	 */
	private String upperCharToUnderLine(String param) {

		if (param == null || param.equals("")) {
			return "";
		}

		Pattern p = Pattern.compile("[A-Z]");

		StringBuilder builder = new StringBuilder(param);

		Matcher mc = p.matcher(param);

		int i = 0;

		while (mc.find()) {
			builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
			i++;
		}

		if ('_' == builder.charAt(0)) {
			builder.deleteCharAt(0);
		}

		return builder.toString();
	}

	/**
	 * 拼接sql
	 * 
	 * @param operation
	 * @param columns
	 * @param tableName
	 * @return
	 */
	private String splicingSQL(String operation, String columns, String tableName) {

		StringBuilder sb = new StringBuilder();
		
		
		if ("insert".equals(operation)) {
			sb.append("insert into ");
			sb.append(tableName);
			sb.append("(");
			sb.append(columns.substring(columns.indexOf(",") + 1, columns.length()));
			sb.append(") values (");
			for (int i = 0; i < columns.split(",").length - 1; i++) {
				if (i == columns.split(",").length - 2) {
					sb.append("?)");
				} else {
					sb.append("?,");
				}
			}
		} else if ("select".equals(operation)) {
			sb.append("select ");
			sb.append(columns);
			sb.append(" from ");
			sb.append(tableName);
		} else if ("update".equals(operation)) {
			/**
			 * 找到表的Key
			 */
			String keyParts = columns.split("<:::::>")[0];
			columns = columns.split("<:::::>")[1];
			sb.append("update ");
			sb.append(tableName);
			sb.append(" set ");
			sb.append(columns);
			sb.append(" where ");
			sb.append(keyParts);
		} else if ("delete".equals(operation)) {
			sb.append("delete from ");
			sb.append(tableName);
			sb.append("where");

		}

		return sb.toString();
	}
}
