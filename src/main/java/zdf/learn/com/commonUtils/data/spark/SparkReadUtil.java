package zdf.learn.com.commonUtils.data.spark;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.max;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;

import zdf.learn.com.commonUtils.pojo.MonthTempData;
import zdf.learn.com.commonUtils.pojo.TripCanStrPojo;
import zdf.learn.com.commonUtils.utils.SparkUtils;

public class SparkReadUtil {

	public static Dataset<Row> readLastTripData(SparkSession spark, String OrgUrl, String NewUrl) {
		Dataset<Row> lastTripDs = null;
		try {
			Dataset<Row> oldLastTrip = spark.read().parquet(OrgUrl);
			Dataset<Row> newLastTrip = spark.read().parquet(NewUrl).select(col("vehicle_id"), col("ig_off"))
					.groupBy("vehicle_id").agg(max("ig_off").as("last_trip_time"))
					.select("vehicle_id", "last_trip_time");
			lastTripDs = oldLastTrip.union(newLastTrip).orderBy(col("last_trip_time").desc()).groupBy("vehicle_id")
					.agg(max("last_trip_time").as("last_trip_time")).select("vehicle_id", "last_trip_time")
					.orderBy(col("last_trip_time").desc()).groupBy("vehicle_id")
					.agg(count(col("last_trip_time")).as("vinCnt")).select("vehicle_id", "vinCnt");
		} catch (Exception e) {
			System.out.println("spark read older ver faild:" + e.getMessage());
		}
		return lastTripDs;
	}

	public static Dataset<Row> readByTripCan(SparkSession spark, String[] OrgUrl) {
		return spark.read().option("basePath", "E:\\gtmc2\\land\\").parquet(OrgUrl).drop(col("target_date"))
				.withColumn("target_date", SparkUdfs.toFullTargetDate.apply(col("targetdate")))
				.select(col("targetdate"), col("target_date"), col("ig_on"), col("ig_off"), col("driving_time"),
						col("fuel_efficiency"), col("max_throttle_open_degree"), col("total_throttle_open_degree"),
						col("throttle_data_records").cast(DataTypes.IntegerType),
						col("sudden_brake_times").cast(DataTypes.IntegerType),
						col("eco_mode_time").cast(DataTypes.IntegerType),
						col("normal_mode_time").cast(DataTypes.IntegerType),
						col("sport_mode_time").cast(DataTypes.IntegerType),
						col("power_mode_time").cast(DataTypes.IntegerType),
						col("snow_mode_time").cast(DataTypes.IntegerType),
						col("inner_circulation_time").cast(DataTypes.IntegerType),
						col("outer_circulation_time").cast(DataTypes.IntegerType),
						col("wiper_use_time").cast(DataTypes.IntegerType), col("odo_trip").cast(DataTypes.IntegerType),
						col("odo_latest").cast(DataTypes.IntegerType), col("type"),
						col("air_conditioning_use_time").cast(DataTypes.IntegerType),
						col("max_speed").cast(DataTypes.IntegerType), col("assist_data_time"), col("vehicle_id"))

				.repartition(col("vehicle_id"));
	}

	/**
	 * 读取Azure数据
	 * @param spark
	 * @param url
	 * @param options
	 * @return
	 */
	public Dataset<Row> readAzureBackFile(SparkSession spark, String url, Map<String, String> options) {
		if(options==null) {
			return spark.read().parquet(url);
		}else {
			return spark.read().options(options).parquet(url);
		}
		
	}

	/**
	 * 计算HCR数据
	 * @param spark
	 */
	public Dataset<Row> computeHcrData(SparkSession spark,Dataset<Row> resultDataSet) {
//		Dataset<Row> resultDataSet= spark.read().parquet("E:\\gtmc\\hcr\\weeklytripcan\\202306\\*\\");
//		 resultDataSet
//		 .filter(col("vehicle_id").equalTo("F9DA4B9E6KG005220")).show(2000, false);
		String[] urlArray = new String[] { "E:\\gtmc2\\land\\" };
		List<MonthTempData> monthTemp = resultDataSet
				.mapPartitions((MapPartitionsFunction<Row, TripCanStrPojo>) m -> {
					List<TripCanStrPojo> listIn = new ArrayList<>();
					while (m.hasNext()) {
						listIn.add(SparkParseUtils.rowParseTo(m.next()));
					}
					return listIn.iterator();
				}, Encoders.bean(TripCanStrPojo.class))
				.mapPartitions((MapPartitionsFunction<TripCanStrPojo, MonthTempData>) m -> {
					List<TripCanStrPojo> listTrip = new ArrayList<TripCanStrPojo>();

					List<MonthTempData> weeklyTemp = new ArrayList<>();
					while (m.hasNext()) {
						listTrip.add(m.next());
					}
					if (listTrip.size() <= 0) {
						return weeklyTemp.iterator();
					}
					List<TripCanStrPojo> resumeList = listTrip.parallelStream().filter(f -> {
						return true;
					}).collect(Collectors.toList());
					List<String> dataRange = new ArrayList<>();
					dataRange.add("20230704");
					SparkUtils.processDataSaveTemp(resumeList, dataRange, 1, 202306, weeklyTemp);
					return weeklyTemp.iterator();
				}, Encoders.bean(MonthTempData.class)).collectAsList();
		return spark.createDataFrame(monthTemp, MonthTempData.class);


	}
}
