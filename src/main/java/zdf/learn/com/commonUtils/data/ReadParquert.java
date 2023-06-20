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
			
			spark.read().parquet("E:\\console\\can-a\\data\\outside\\hash=0a\\timeKey=2023060116\\vin=TESTVINFPJIQ00003597\\").show(500,false);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
