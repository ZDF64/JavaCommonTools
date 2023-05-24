package zdf.learn.com.commonUtils.data.parquet;

import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.udf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataTypes;

public class ReadSeedCluster {
	public static Supplier<String> randomNumberStr = ()->{
		return String.valueOf((int)(Math.random()*10+48));
		};
	public static Supplier<String> randomCharStr = ()->{
		return String.valueOf((int)(Math.random()*26+68));
		};
	public static UserDefinedFunction stringRebase = udf((String str) -> str.substring(0)+"",DataTypes.StringType);
	public static UserDefinedFunction vinRebase = udf((String str) -> {
		return randomNumberStr.get()
			   + randomCharStr.get()
	           + randomNumberStr.get()
			   + randomCharStr.get()
	           + randomNumberStr.get()
			   + randomCharStr.get()
			   + randomNumberStr.get()
			   + randomNumberStr.get()
			   + randomNumberStr.get()
			   + randomNumberStr.get()
			   + randomNumberStr.get()
			   + randomNumberStr.get()
			   + randomCharStr.get()
	           + randomNumberStr.get()
	           + randomNumberStr.get()
	           + randomNumberStr.get()
			   + randomCharStr.get();
	},DataTypes.StringType);
	public static void main(String[] args) {
		try(SparkSession spark = SparkSession.builder()
				.master("local[3]")
				.appName(ReadSeedCluster.class.getName())
				.getOrCreate()){
			Properties prop = new Properties();
			prop.put("driver", "com.mysql.cj.jdbc.Driver");
			prop.put("user", "root");
			prop.put("password", "jXuuHEMe84");
			prop.put("allowPublicKeyRetrieval", "true");
			Dataset<Row> jdbc = spark.read().jdbc("jdbc:mysql://172.31.64.116:3306/cnhcrdb", 
					"velocity_destribution_month_inf", 
					"aggregate_year_month", 
					202207, 202307, 
					3, prop);
			jdbc.show();
			
//			Dataset<Row> tripCan = spark.read().format("jdbc")
//					.option("url", "jdbc:mysql://172.31.64.116:3306/cnhcrdb?useUnicode=true&characterEncoding=utf8&useSSL=true")
//					.option("user", "root")
//					.option("password", "jXuuHEMe84")
//					.option("dbtable", "(select * from velocity_destribution_month_inf ) as T")
//					.option("driver", "com.mysql.cj.jdbc.Driver")
//					.option("lowerBound", "1")
//					.option("upperBound", "40000")
//					.option("numPartitions", 4)
//					.option("partitionColumn", "velocity_destribution_month_inf_id").load();
//			tripCan.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * jdbc扩增
	 * @param seed         基础种子
	 * @param augmentRate  扩增倍率
	 */
	public Dataset<Row> jdbcAugment(Dataset<Row> seed , int augmentRate) {
		Dataset<Row> seedInner = seed;
		for(int i = 0 ; i < augmentRate; i ++) {
			seedInner
			.withColumn("vehicle_id_org",col("vehicle_id"))
			.drop(col("vehicle_id"))
			.withColumn("vehicle_id", vinRebase.apply(col("vehicle_id_org")))
			.drop(col("vehicle_id_org"));
			seed.union(seedInner);
		}
		return seed;
	}
}
