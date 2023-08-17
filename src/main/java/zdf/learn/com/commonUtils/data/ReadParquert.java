package zdf.learn.com.commonUtils.data;

import static org.apache.spark.sql.functions.*;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionUtil;
import scala.Function1;
import scala.annotation.meta.setter;
import scala.collection.parallel.ParIterableLike.GroupBy;
import scala.runtime.BoxedUnit;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.data.spark.SparkReadUtil;
import zdf.learn.com.commonUtils.data.spark.SparkUdfs;
import zdf.learn.com.commonUtils.pojo.AirConditioningUseTimeInf;
import zdf.learn.com.commonUtils.pojo.CirculationDestributionInf;
import zdf.learn.com.commonUtils.pojo.CustomerAppMonthInf;
import zdf.learn.com.commonUtils.pojo.DrivingDayPerFrequencyInf;
import zdf.learn.com.commonUtils.pojo.DrivingDayPerMilageInf;
import zdf.learn.com.commonUtils.pojo.DrivingTimezoneInf;
import zdf.learn.com.commonUtils.pojo.DrvDayPerDrivingTimeInf;
import zdf.learn.com.commonUtils.pojo.MilageDayPerMonthInf;
import zdf.learn.com.commonUtils.pojo.MonthTempData;
import zdf.learn.com.commonUtils.pojo.PowerSportUseTimeInf;
import zdf.learn.com.commonUtils.pojo.RmtSrvMilagePerMonthInf;
import zdf.learn.com.commonUtils.pojo.SuddenBrakeTimesInf;
import zdf.learn.com.commonUtils.pojo.TripCanStrPojo;
import zdf.learn.com.commonUtils.pojo.WiperUseTimeInf;
import zdf.learn.com.commonUtils.utils.HcrUtil;
import zdf.learn.com.commonUtils.utils.SparkUtils;

public class ReadParquert {

	Dataset<Row> resultDataSet;
	
	public static void main(String[] args) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

//		System.out.println(LocalDateTime.parse("20230619133001", format).toInstant(ZoneOffset.of("+8")).toEpochMilli());
		Float fs = 12.9f;
		System.out.println(fs.intValue());
		// TODO Auto-generated method stub
		try (SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date()).master("local[6]").getOrCreate();
				JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());) {
//			spark.conf().set("spark.sql.session.timeZone", "UTC");
			spark.sparkContext().conf().set("spark.driver.maxResultSize", "4G");

//			makeAzureVelocityData(spark);
//			makeAzureTripCanData(spark);
			Dataset<Row> CanEDs = readCanETripCanData(spark).withColumn("tab", lit(1));
			Dataset<Row> AzureBakDs =readAzureTripCanData(spark).withColumn("tab", lit(2));

//			//LVGBPB9E8LG435565
			Dataset<Row> allDs = CanEDs.union(AzureBakDs);
			allDs
//			Long allLong = allDs
			.groupBy(col("vehicle_id"),col("target_date"), col("ig_on"), col("ig_off"), col("driving_time"),
				col("fuel_efficiency"), col("max_throttle_open_degree"), col("total_throttle_open_degree"),
				col("throttle_data_records"), col("sudden_brake_times"), col("eco_mode_time"),
				col("normal_mode_time"), col("sport_mode_time"), col("power_mode_time"), col("snow_mode_time"),
				col("inner_circulation_time"), col("outer_circulation_time"), col("wiper_use_time"),
				col("odo_trip"), col("odo_latest"), col("type"), col("air_conditioning_use_time"), col("max_speed"),
				col("assist_data_time").cast(DataTypes.IntegerType))
			.agg(count(col("vehicle_id")).as("cnt"),sum(col("tab")).as("tables"))
			.where(col("cnt").notEqual(2))
			.orderBy(col("vehicle_id"),col("ig_on"),col("ig_off"))
//			.count();
//			System.out.println(allLong);
			.repartition(col("vehicle_id"))
			.collectAsList()
			.forEach(f->{
				DefangFileHandle dfTool = new DefangFileHandle();
					Row r =f;
					dfTool.toWrite(r.getString(0)+","+r.getDate(1)+","+r.getTimestamp(2)+","+r.getTimestamp(3)
					+","+r.get(4)+","+r.get(5)+","+r.get(6)+","+r.get(7)+","+r.get(8)+","+r.get(9)+","+r.get(10)
					+","+r.get(11)+","+r.get(12)+","+r.get(13)+","+r.get(14)+","+r.get(15)+","+r.get(16)+","+r.get(17)+","+r.get(18)+","+r.get(19)+","+r.get(20)
					+","+r.get(21)+","+r.get(22)+","+r.get(23)+","+r.get(24)+","+r.get(25)
					, "E:\\gtmc\\unBalanceOrder.csv", true);				
			});
//			.show(2000, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void show(int numRows,boolean truncate) {
		this.resultDataSet.show(numRows, truncate);
	}
	
//	去重
	public void duplicateTripcan(SparkSession spark,Dataset<Row> TripCan) {
		HashSet<String> duplicateVins = new HashSet<String>();
		DefangFileHandle dfTools = new DefangFileHandle();
		dfTools.toWrite("trip can cnt org :"+TripCan.count(), "E:\\gtmc\\tripCanCntOrg.csv", true);
		TripCan.groupBy(col("vehicle_id"),col("ig_on"))
		.agg(count(col("vehicle_id")).as("cnt"))
		.where(col("cnt").$greater(1))
		.foreachPartition((ForeachPartitionFunction<Row>)f->{
			DefangFileHandle dfTool = new DefangFileHandle();
			while(f.hasNext()) {
				Row r =f.next();
				dfTool.toWrite(r.getString(0)+","+r.getTimestamp(1)+","+r.getLong(2), "E:\\gtmc\\distinctListBefore.csv", true);
			}
		});
		dfTools.readToLine("E:\\gtmc\\distinctListBefore.csv").forEach(f->{
			duplicateVins.add(f.split(",")[0]);
		});
		Dataset<Row> TripCanAfter = hcrTripCanDistinct(TripCan).cache();
		dfTools.toWrite("trip can cnt After :"+TripCanAfter.count(), "E:\\gtmc\\tripCanCntAfter.csv", true);
		TripCanAfter
		.filter((FilterFunction<Row>)f->{
			String vin = f.getString(23);
			return duplicateVins.contains(vin);
		})
		.groupBy(col("vehicle_id"),col("ig_on"))
		.agg(count(col("vehicle_id")).as("cnt"))
		.where(col("cnt").$greater(1))
		.foreachPartition((ForeachPartitionFunction<Row>)f->{
			DefangFileHandle dfTool = new DefangFileHandle();
			while(f.hasNext()) {
				Row r =f.next();
				dfTool.toWrite(r.getString(0)+","+r.getTimestamp(1)+","+r.getLong(2), "E:\\gtmc\\distinctListAfterByHash.csv", true);
			}
		});
	}
	
	public static Dataset<Row> hcrTripCanDistinct(Dataset<Row> orgData){
		Dataset<Row> dataSet = orgData.select(
				col("target_date"), col("ig_on"), col("ig_off"), col("driving_time"),
				col("fuel_efficiency"), col("max_throttle_open_degree"), col("total_throttle_open_degree"),
				col("throttle_data_records"), col("sudden_brake_times"), col("eco_mode_time"),
				col("normal_mode_time"), col("sport_mode_time"), col("power_mode_time"), col("snow_mode_time"),
				col("inner_circulation_time"), col("outer_circulation_time"), col("wiper_use_time"),
				col("odo_trip"), col("odo_latest"), col("type"), col("air_conditioning_use_time"), col("max_speed"),
				col("assist_data_time").cast(DataTypes.IntegerType), col("vehicle_id"))
		.dropDuplicates("vehicle_id","ig_on","ig_off")
		.repartition(col("vehicle_id"));
		StructType schema = dataSet.schema();
		System.out.println(schema);
		return dataSet.mapPartitions((MapPartitionsFunction<Row,Row>) m->{
			Map<String,Row> partMap = new HashMap<String,Row>();
			while(m.hasNext()) {
				Row innerRow = m.next();
				String disTinctKeys = innerRow.getString(23) + innerRow.getTimestamp(1).toString();
				if(partMap.containsKey(disTinctKeys)) {
					Row mapRow = partMap.get(disTinctKeys);
					if(mapRow.getTimestamp(2).before(innerRow.getTimestamp(2))) {
						partMap.put(disTinctKeys, innerRow);
					}
				}else {
					partMap.put(disTinctKeys, innerRow);
				}
			}
			return partMap.values().iterator();
		}, RowEncoder.apply(schema));
	}
	
	
	
	public static Dataset<Row> readAzureTripRemoteData(SparkSession spark,String ...vins) {
		return spark.read()
		.option("header", "true").option("basePath", "E:\\gtmc\\land\\remote\\trip_remote")
		.csv("E:\\gtmc\\land\\remote\\trip_remote\\")
		
		;
	}
	


	
	public static Dataset<MonthTempData> hcrMonthFetch(Dataset<Row> tripCanDs,List<String> dataRange){
		Set<String> dataRangeSet = dataRange.stream().collect(Collectors.toSet());
		Dataset<MonthTempData> hcrComputeDS = tripCanDs
			.select(
				col("target_date"), col("ig_on"), col("ig_off"), col("driving_time"),
				col("fuel_efficiency"), col("max_throttle_open_degree"), col("total_throttle_open_degree"),
				col("throttle_data_records"), col("sudden_brake_times"), col("eco_mode_time"),
				col("normal_mode_time"), col("sport_mode_time"), col("power_mode_time"), col("snow_mode_time"),
				col("inner_circulation_time"), col("outer_circulation_time"), col("wiper_use_time"),
				col("odo_trip"), col("odo_latest"), col("type"), col("air_conditioning_use_time"), col("max_speed"),
				col("assist_data_time"), col("vehicle_id")
			)
			.repartition(col("vehicle_id"))
			.mapPartitions((MapPartitionsFunction<Row,TripCanStrPojo>)ms->{
				List<TripCanStrPojo> listIn = new ArrayList<>();
				while (ms.hasNext()) {
					try {
						listIn.add(HcrUtil.rowParseTo(ms.next()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				return listIn.iterator();
		}, Encoders.bean(TripCanStrPojo.class))
		.mapPartitions((MapPartitionsFunction<TripCanStrPojo, MonthTempData>) m -> {
			List<TripCanStrPojo> listTrip = new ArrayList<TripCanStrPojo>();
			List<MonthTempData> weeklyTemp = new ArrayList<>();
			while (m.hasNext()) {
				listTrip.add(m.next());
			}
			if (listTrip.size() <= 0){
				return weeklyTemp.iterator();
			}
			List<TripCanStrPojo> resumeList = listTrip.parallelStream().filter(f->{
				return dataRangeSet.contains(f.getTargetDate().replaceAll("-", ""));
			}).collect(Collectors.toList());
			if(resumeList.size()>0) {
				SparkUtils.processDataSaveTemp(resumeList, dataRange, 2, 202307, weeklyTemp);
			}
			return weeklyTemp.iterator();
		}, Encoders.bean(MonthTempData.class));
		
		return hcrComputeDS;
	}
	
	public static void readTripCanHw(SparkSession spark) {
//		E:\dev_cache\tripcan0812
		spark.read()
				.parquet("E:\\dev_cache\\tripcan0812\\")
				.select(col("vehicle_id"),col("target_date"),col("ig_on"),col("ig_off"))
				.foreachPartition((ForeachPartitionFunction<Row>)f->{
						DefangFileHandle dfTool = new DefangFileHandle();
						while(f.hasNext()) {
							Row r =f.next();
							dfTool.toWrite(r.getString(0)+","+r.getDate(1)+","+r.getTimestamp(2)+","+r.getTimestamp(3), "E:\\dev_cache\\tripcan0812\\result.csv", true);
						}
					}
				);
	}
	public static Dataset<Row> readCanETripCanData(SparkSession spark){
		return spark.read().option("basePath", "E:\\dev_cache\\trip_can\\")
				.parquet("E:\\dev_cache\\trip_can\\")
				.where(col("target_date").equalTo("2023-07-28"))
				.withColumn("target_date", SparkUdfs.toFullTargetDate.apply(col("targetdate")))
				.withColumn("assist_data_time", lit(20230810))
				.select(
						col("target_date"), 
						col("vehicle_id"),
						col("ig_on"), 
						col("ig_off"), 
						col("driving_time").cast(DataTypes.IntegerType),
						col("fuel_efficiency"),
						col("max_throttle_open_degree"), 
						col("total_throttle_open_degree"),
						col("throttle_data_records").cast(DataTypes.IntegerType), 
						col("sudden_brake_times").cast(DataTypes.IntegerType), 
						col("eco_mode_time").cast(DataTypes.IntegerType),
						col("normal_mode_time").cast(DataTypes.IntegerType),
						col("sport_mode_time").cast(DataTypes.IntegerType), 
						col("power_mode_time").cast(DataTypes.IntegerType), 
						col("snow_mode_time").cast(DataTypes.IntegerType),
						col("inner_circulation_time").cast(DataTypes.IntegerType), 
						col("outer_circulation_time").cast(DataTypes.IntegerType), 
						col("wiper_use_time").cast(DataTypes.IntegerType),
						col("odo_trip").cast(DataTypes.IntegerType), 
						col("odo_latest").cast(DataTypes.IntegerType), 
						col("type"), 
						col("air_conditioning_use_time").cast(DataTypes.IntegerType), 
						col("max_speed").cast(DataTypes.IntegerType),
						col("assist_data_time"), 
						col("targetdate")
						)
				.distinct();
	}
	
	
	public static Dataset<Row> readAzureTripCanData(SparkSession spark){
		try {

		return spark.read().option("basePath", "E:\\gtmc\\trip_can202307\\")
				.parquet("E:\\gtmc\\trip_can202307\\targetdate=2023072*")
				.distinct()
		.where(col("target_date").equalTo("2023-07-28"))
//		.filter(col("vehicle_id").isin(vins))//"LVGB1B0E0PG403116","JTNHS3DH0M8052455",
//		.filter(col("type").isNotNull().and(col("type").notEqual("")))
		.withColumn("target_date", SparkUdfs.toFullTargetDate.apply(col("targetdate")))
		.withColumn("assist_data_time", lit(20230810))
		.select(
				col("target_date"), 
				col("vehicle_id"),
				functions.dateSetOffByHour.apply(col("ig_on"),lit(8)).as("ig_on"),
				functions.dateSetOffByHour.apply(col("ig_off"),lit(8)).as("ig_off"),
				col("driving_time").cast(DataTypes.IntegerType),
				col("fuel_efficiency"),
				col("max_throttle_open_degree"), 
				col("total_throttle_open_degree"),
				col("throttle_data_records").cast(DataTypes.IntegerType), 
				col("sudden_brake_times").cast(DataTypes.IntegerType), 
				col("eco_mode_time").cast(DataTypes.IntegerType),
				col("normal_mode_time").cast(DataTypes.IntegerType),
				col("sport_mode_time").cast(DataTypes.IntegerType), 
				col("power_mode_time").cast(DataTypes.IntegerType), 
				col("snow_mode_time").cast(DataTypes.IntegerType),
				col("inner_circulation_time").cast(DataTypes.IntegerType), 
				col("outer_circulation_time").cast(DataTypes.IntegerType), 
				col("wiper_use_time").cast(DataTypes.IntegerType),
				col("odo_trip").cast(DataTypes.IntegerType), 
				col("odo_latest").cast(DataTypes.IntegerType), 
				col("type"), 
				col("air_conditioning_use_time").cast(DataTypes.IntegerType), 
				col("max_speed").cast(DataTypes.IntegerType),
				col("assist_data_time"), 
				col("targetdate")
				)
		.distinct();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void makeAzureVelocityData(SparkSession spark){
		Dataset<Row> velocityData = spark.read().parquet("E:\\gtmc\\azurebak\\202308*\\trip_can_velocity_time\\*\\")
				.withColumn("targetdate", SparkUdfs.dateToyyyyMMdd.apply(col("target_date")))
				.withColumn("assist_data_time", lit(20230718));
		velocityData.show(20,false);
		velocityData
		.repartition(1)
		.write()
		.partitionBy("targetdate","assist_data_time")
		.mode(SaveMode.Append)
		.parquet("E:\\gtmc\\trip_velocity202308\\");
	}
	public static void makeAzureTripCanData(SparkSession spark){
		Dataset<Row> TripCanData = spark.read().parquet("E:\\gtmc\\azurebak\\202307*\\trip_can\\*\\")
				.withColumn("targetdate", SparkUdfs.dateToyyyyMMdd.apply(col("target_date")))
				.withColumn("assist_data_time", lit(20230816));
		TripCanData.show(200, false);
		TripCanData
		.repartition(1)
		.write()
		.partitionBy("targetdate","assist_data_time")
		.mode(SaveMode.Append)
		.parquet("E:\\gtmc\\trip_can202307\\");
	}
	
	
	public static void computTripToHCRShow(SparkSession spark) {
		SparkReadUtil sparkutils = new SparkReadUtil();
		Dataset<Row> TripCan = sparkutils.readAzureBackFile(spark, "E:\\gtmc2\\Azure\\*\\trip_can\\*\\*", null);
		TripCan = TripCan
				.withColumn("targetdate", SparkUdfs.dateToyyyyMMdd.apply(col("target_date")))
				.select(
				col("target_date"), 
				col("ig_on"),
				col("ig_off"), 
				col("driving_time").cast(DataTypes.IntegerType),
				col("fuel_efficiency"),
				col("max_throttle_open_degree"), 
				col("total_throttle_open_degree"),
				col("throttle_data_records").cast(DataTypes.IntegerType), 
				col("sudden_brake_times").cast(DataTypes.IntegerType), 
				col("eco_mode_time").cast(DataTypes.IntegerType),
				col("normal_mode_time").cast(DataTypes.IntegerType),
				col("sport_mode_time").cast(DataTypes.IntegerType), 
				col("power_mode_time").cast(DataTypes.IntegerType), 
				col("snow_mode_time").cast(DataTypes.IntegerType),
				col("inner_circulation_time").cast(DataTypes.IntegerType), 
				col("outer_circulation_time").cast(DataTypes.IntegerType), 
				col("wiper_use_time").cast(DataTypes.IntegerType),
				col("odo_trip").cast(DataTypes.IntegerType), 
				col("odo_latest").cast(DataTypes.IntegerType), 
				col("type"), 
				col("air_conditioning_use_time").cast(DataTypes.IntegerType), 
				col("max_speed").cast(DataTypes.IntegerType),
//				col("assist_data_time"), 
				col("vehicle_id"),
				col("targetdate")
				)
				.withColumn("assist_data_time", lit(20230714))
				.repartition(col("vehicle_id"));
		TripCan.repartition(1)
		.write()
		.partitionBy("targetdate","assist_data_time")
		.mode(SaveMode.Append)
		.parquet("E:\\gtmc\\hcr");
//		Dataset<Row> tempSaveDs = sparkutils.computeHcrData(spark, TripCan);
//		.where(col("target_date").$less("2023-07-01"))
//		tempSaveDs.repartition(1).write().mode(SaveMode.Overwrite).save("E:\\gtmc\\hcr\\weekly2");
	}
	
		

}
