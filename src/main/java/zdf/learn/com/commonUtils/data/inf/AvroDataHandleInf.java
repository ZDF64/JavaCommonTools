package zdf.learn.com.commonUtils.data.inf;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface AvroDataHandleInf {
	public <T> Dataset<Row> makeAvro(SparkSession spark,JavaRDD<List<T>> rdd);
	public void uploadAvro(Dataset<Row> ds,String savePrefix);
}
