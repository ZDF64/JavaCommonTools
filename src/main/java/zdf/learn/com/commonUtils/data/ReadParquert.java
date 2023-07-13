package zdf.learn.com.commonUtils.data;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.max;
import static org.apache.spark.sql.functions.sum;
import static org.apache.spark.sql.functions.size;
import static org.apache.spark.sql.functions.udf;
import static org.apache.spark.sql.functions.when;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

import scala.annotation.meta.setter;
import scala.collection.parallel.ParIterableLike.GroupBy;
import zdf.learn.com.commonUtils.pojo.MonthTempData;
import zdf.learn.com.commonUtils.pojo.TripCanStrPojo;

public class ReadParquert {

	public static void main(String[] args) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
//		System.out.println(LocalDateTime.parse("20230619133001", format).toInstant(ZoneOffset.of("+8")).toEpochMilli());
		Float fs = 12.9f;
		System.out.println(fs.intValue());
		// TODO Auto-generated method stub
		try (SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date()).master("local[6]").getOrCreate();
				JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
				) {
			spark.conf().set("spark.sql.session.timeZone", "UTC");
//			 Dataset<Row> resultDataSet = spark.read().parquet("E:\\gtmc\\hcr\\weeklytripcan\\202306\\*\\");
//			 resultDataSet
//			 .filter(col("vehicle_id").equalTo("F9DA4B9E6KG005220")).show(2000, false);
			String[] urlArray = new String[] {
					"E:\\G\\land\\trip_can\\"
			};
			
			Dataset<Row> vinDs = spark.read().option("header", "true")
					.csv("E:\\compareResult\\vin.csv").withColumn("vin", col("vehicle_id")).drop(col("vehicle_id"));
			
			Dataset<Row> tripDs = readByTripCan(spark, urlArray);
			tripDs.join(vinDs,tripDs.col("vehicle_id").equalTo(vinDs.col("vehicle_md5")))
////			.filter(col("vehicle_id").equalTo("F9DA4B9E6KG005220"))
			.drop(col("vehicle_id"))
			.withColumn("vehicle_id", col("vin"))
			.drop(col("vin"))
			.repartition(1)
			.write()
			.partitionBy("targetdate","assist_data_time")
			.mode(SaveMode.Append)
			.parquet("E:\\gtmc2\\land\\");
//			.show(2000, false);
			
			}catch (Exception e) {
			e.printStackTrace();
			}
		
	}
	
	public static void readShow(String url) {
		
	}
	public static Dataset<Row> readLastTripData(SparkSession spark,String OrgUrl,String NewUrl) {
		Dataset<Row> lastTripDs = null;
		try {
			Dataset<Row> oldLastTrip =  spark.read().parquet(OrgUrl);
			Dataset<Row> newLastTrip = spark.read().parquet(NewUrl)
												.select(col("vehicle_id"),col("ig_off"))
												.groupBy("vehicle_id")
								                .agg(max("ig_off").as("last_trip_time"))
								                .select("vehicle_id", "last_trip_time");
			lastTripDs = oldLastTrip.union(newLastTrip).orderBy(col("last_trip_time").desc())
										.groupBy("vehicle_id")
							            .agg(max("last_trip_time").as("last_trip_time"))
							            .select("vehicle_id", "last_trip_time").orderBy(col("last_trip_time").desc())
							            .groupBy("vehicle_id")
							            .agg(count(col("last_trip_time")).as("vinCnt"))
							            .select("vehicle_id", "vinCnt");
		} catch (Exception e) {
			System.out.println("spark read older ver faild:"+e.getMessage());
		}
		return lastTripDs;
	}
	
	public static Dataset<Row> readByTripCan(SparkSession spark,String[] OrgUrl) {
		return spark.read().option("basePath","E:\\G\\land\\trip_can")
				.parquet(OrgUrl)
				.drop(col("target_date"))
				.withColumn("target_date", toFullTargetDate.apply(col("targetdate")))
				.select(
				col("targetdate"),
				col("target_date"), 
				col("ig_on"), col("ig_off"), col("driving_time"),
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
				col("vehicle_id"))
				
				.repartition(col("vehicle_id"));
    }
	
	public static UserDefinedFunction makeVin = udf((String vin) -> {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(vin.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
			}
			return result.substring(0,5).toUpperCase() + vin.substring(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	},DataTypes.StringType);
	public static UserDefinedFunction dateToyyyyMMdd = udf((Date date) -> {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(date);
		} , DataTypes.StringType);
	public static UserDefinedFunction long_to_date = udf((Long lng) -> new Date(lng), DataTypes.DateType);
	public static UserDefinedFunction toFullTargetDate = udf((Integer tar) -> {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tarStr = tar+"";
		String targetDate = tarStr.substring(0, 4)+"-"+tarStr.subSequence(4, 6)+"-"+tarStr.substring(6, 8);
		return new java.sql.Date(simpleDateFormat.parse(targetDate).getTime());
	}, DataTypes.DateType);
	
	private static Column getNotEmptyListCol(Object[] labels) {
//    	Speed,Speed_TypeA,Speed_TypeB
        Column col = null;

        for (int i = 0; i < labels.length; i++) {

            String columnName = labels[i].toString();

            if (i == 0) {
                col = when(size(col(columnName)).gt(0), col(columnName));
            } else if (i == labels.length - 1) {
                col = col.otherwise(col(columnName));
            } else {
                col = col.when(size(col(columnName)).gt(0), col(columnName));
            }

        }

        return col;

    }
	public static TripCanStrPojo rowParseTo(Row r) {
		Timestamp igOn = r.getTimestamp(1);
		Timestamp igOff = r.getTimestamp(2);
		String type = r.getString(19);
		TripCanStrPojo trip = new TripCanStrPojo();
		trip.setTargetDate(r.getDate(0).toLocalDate().toString());
		trip.setIgOn(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(igOn.toInstant(), ZoneOffset.of("+08:00"))));
		trip.setIgOff(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(igOff.toInstant(), ZoneOffset.of("+08:00"))));
		trip.setDrivingTime(r.getInt(3));
		trip.setFuelEfficiency(r.getDouble(4));
		trip.setMaxThrottleOpenDegree((float) r.getDouble(5));
		trip.setTotalThrottleOpenDegree(r.getDouble(6));
		trip.setThrottleDataRecords((int) r.getInt(7));
		trip.setSuddenBrakeTimes(r.getInt(8));
		trip.setEcoModeTime( r.getInt(9));
		trip.setNormalModeTime(r.getInt(10));
		trip.setSportModeTime( r.getInt(11));
		trip.setPowerModeTime( r.getInt(12));
		trip.setSnowModeTime( r.getInt(13));
		trip.setInnerCirculationTime(r.getInt(14));
		trip.setOuterCirculationTime(r.getInt(15));
		trip.setWiperUseTime(r.getInt(16));
		trip.setOdoTrip(r.getInt(17));
		trip.setOdoLatest(r.getInt(18));
		trip.setType(type);
		trip.setAirConditioningUseTime(r.getInt(20));
		trip.setMaxSpeed(r.getInt(21));
		trip.setAssistDataTime(r.getInt(22)+"");
		trip.setVehicleId(r.getString(23));
		return trip;
	}
	public static MonthTempData rowToTempData(Row r) {
		MonthTempData temp = new MonthTempData();
		temp.setVehicle_id(r.getString(0));
		temp.setAggregate_year_month(r.getInt(1));
		temp.setAggregate_week(r.getInt(2));
		temp.setVehcile_type(r.getString(3));
		temp.setOdo_trip(r.getInt(4));
		temp.setOdo_latest(r.getInt(5));
		temp.setFuel_efficiency(r.getDouble(6));
		temp.setTotal_milage(r.getInt(7));
//		temp.setAvgFuleWeek(r.getDouble(8));
		temp.setDriving_days(r.getInt(9));
		temp.setDaily_driving_times(r.getList(10));
		temp.setDaily_driving_milage(r.getList(11));
		temp.setDaily_driving_duration(r.getList(12));
		temp.setDriving_time(r.getInt(13));
		temp.setEffective_driving_time(r.getInt(14));
		temp.setWiper_use_time(r.getDecimal(15));
		temp.setAir_conditioning_use_time(r.getDecimal(16));
		temp.setInner_circulation_time(r.getInt(17));
		temp.setOuter_circulation_time(r.getInt(18));
		temp.setSport_mode_time(r.getInt(19));
		temp.setDriving_period_duration(r.getList(20));
		temp.setSudden_brake_times(r.getInt(21));
		temp.setMax_speed(r.getInt(22));
		temp.setMax_throttle_open_degree(r.getFloat(23));
		temp.setAvg_throttle_open_degree(r.getFloat(24));
		temp.setTotal_driving_times(r.getInt(25));
		return temp;
	}
	 @SafeVarargs
	    private static <T> T[] concatAll(T[] first, T[]... rest) {
	        int totalLength = first.length;
	        for (T[] array : rest) {
	            totalLength += array.length;
	        }
	        T[] result = Arrays.copyOf(first, totalLength);
	        int offset = first.length;
	        for (T[] array : rest) {
	            System.arraycopy(array, 0, result, offset, array.length);
	            offset += array.length;
	        }
	        return result;
	    }
}

