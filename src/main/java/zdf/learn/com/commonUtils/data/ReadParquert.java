package zdf.learn.com.commonUtils.data;

import java.util.Date;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class ReadParquert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date()).master("local[6]").getOrCreate();
				JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
				) {
			
			spark.read().option("basePath", "E:\\console\\can-a\\data\\outside").parquet("E:\\console\\can-a\\data\\outside").show();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
