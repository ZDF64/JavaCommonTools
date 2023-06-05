package zdf.learn.com.commonUtils.data;

import static org.apache.spark.sql.functions.*;
import org.apache.spark.sql.expressions.Window;
import static zdf.learn.com.commonUtils.data.functions.date_format_long;
import static zdf.learn.com.commonUtils.data.functions.datetime_to_long;
import static zdf.learn.com.commonUtils.data.functions.toLongTime;
import static zdf.learn.com.commonUtils.data.functions.toMapKeyOnly;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class ParquetCombine {
	static String requiredLabels = "Speed,Speed_TypeA,Speed_TypeB,FuelConsumption,AccelPedalAngle_TypeA,AccelPedalAngle_TypeC,AccelPedalAngle_TypeB,AccelerationFB,AccelerationFB_TypeB,AccelerationFB_TypeC,EcoModeIndicator,DriveModeECO,DriveModeECO_TypeB,SportModeSelect,DriveModeSPORT,DriveModeSPORT_TypeB,PowerModeSelect_TypeA,PowerModeSelect_TypeB,DriveModePOWER,DriveModePOWER_TypeB,SnowModeSelect,RecIndicator,WiperControl,Odometer_km,AirConIndicator";

	public static void main(String[] args) {

		/**
		 * Spark集群模式
		 */
		try (SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date()).master("local[6]").getOrCreate();
				JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
				) {
			// others
			ParquetCombine combine = new ParquetCombine();
			spark.read().format("avro").load("D:\\home\\apuser\\parquet\\a\\procdata\\CN\\real\\can_External\\13\\2023\\06\\01\\16")
			.filter(col("headers").isNotNull())
            .filter((FilterFunction<Row>)r -> {
                
                Map<CharSequence, CharSequence> headers = r.getJavaMap(0);
                CharSequence vin = headers.get("VIN");
                CharSequence requestDateTime = headers.get("RequestDateTime");
                
                if (vin == null || requestDateTime == null) {
                    return false;
                }
                
                try {
                    toLongTime.apply(requestDateTime.toString(), "request");
                } catch (Exception e) {
                    return false;
                }
                
                return true;
                
            })
            .select(col("headers.VIN").as("vin"),
                    col("headers.DispatchModelType").as("dispatchModelType"),
                    datetime_to_long.apply(col("headers.RequestDateTime"), lit("request")).as("requestDateTime"),
                    col("body.dataCapacityList").as("dcList"))
            
//			Dataset<Row> DS_OutSide = spark.read().option("basePath", "D:\\home\\apuser\\data\\readAvro\\").parquet("D:\\home\\apuser\\data\\readAvro\\vin=LVGD31354NG002996");
//			DS_OutSide
			.select(col("vin"), explode(col("dcList")).as("dc"))
	        .select(col("vin"), explode(col("dc.canInformationList")).as("can"))
	        .filter((FilterFunction<Row>)r -> {
	            CharSequence time = r.getStruct(1).getAs("time");
	            if (time == null) {
	            	System.err.println("MessageType.LACK_OF_KEY");
	                return false;
	            }
	            try {
	                toLongTime.apply(time.toString(), "outside");
	            } catch (Exception e) {
	            	System.err.println("MessageType.INVALID_DATA,time=" + time);
	                return false;
	            }
	            return true;
	        })
	        .select(col("vin"), datetime_to_long.apply(col("can.time"), lit("outside")).as("time"), col("can.outsideUseDataMap"))
	        .select(col("vin"), col("time"), explode(col("outsideUseDataMap")))
	        .select(col("vin"), col("time"), col("key").as("label"), col("value"))
	        .filter(lit(requiredLabels).equalTo("").or(col("label").isin((Object[]) requiredLabels.split(","))))
	        .select(col("vin"), col("time"), col("label"), explode(toMapKeyOnly.apply(col("value"))))
	        .select(col("vin"), col("time"), col("label"), col("key").as("value"),
	                md5(col("vin")).substr(length(md5(col("vin"))).minus(1), lit(2)).as("hash"),
	                date_format_long.apply(col("time"), lit("yyyyMMddHH")).as("timeKey"))
	        .repartition(col("hash"), col("timeKey"), col("vin"))
	        .where(col("timeKey").equalTo(2023052401))
	        .show(8500,false);
			
			
//			Dataset<Row> DS_19Others = spark.read().parquet("D:\\home\\apuser\\data\\readAvro\\");
//			Dataset<Row> DS_19mc = spark.read().parquet("D:\\home\\apuser\\data\\readAvro19mc\\");
//			combine.showByHeader(DS_19Others);
//			combine.readByA2Style(spark);
//			spark.read().parquet("D:\\home\\apuser\\data\\S3")
//			.withColumn("vin", lit("LVGD31354NG002996"))
//			.withColumn("timeKey", lit("2023052401"))
//			.withColumn("rowNumber", row_number().over(Window.partitionBy("vin").orderBy("vin")))
//			.show(1500,false);
//			spark.read().parquet("E:\\console\\can-a\\data\\outside\\hash=7a\\timeKey=2023052401\\vin=LVGD31354NG002996")
//			.withColumn("vin", lit("LVGD31354NG002996"))
//			.withColumn("timeKey", lit("2023052401"))
//			.withColumn("rowNumber", row_number().over(Window.partitionBy("vin").orderBy("vin")))
//			.show(1500,false);
//			.show(500,false);
//			
//
//			combine.saveHeader(DS_19Others);
//			combine.saveOutSide(DS_19Others);
//			combine.saveType3(DS_19Others);

//			combine.showHeader(spark, "D:\\home\\apuser\\data\\header\\");
//			Dataset<Row> DS_19mc = spark.read().parquet("D:\\home\\apuser\\data\\readAvro\\part-00000-6650bf3d-fe79-4629-bce8-9733fcdf11ea-c000.snappy.parquet");
//			combine.saveHeader(DS_19mc);
//			combine.saveOutSide(DS_19mc);
//			combine.saveType3(DS_19mc);
			
			/**
			 * 验证，确实有重复的内容的parquet文件
			 */
//			spark.read().option("basePath", "D:\\home\\apuser\\data\\header\\")
//			.parquet("D:\\home\\apuser\\data\\header\\hash=7a\\timeKey=2023052409\\vin=LVGD31354NG002996\\part-00000-21d5f7bf-1efa-4a25-8b0d-4455ed5846cd.c000.snappy.parquet")
//			.show(500,false);
//			spark.read().option("basePath", "D:\\home\\apuser\\data\\header\\")
//			.parquet("D:\\home\\apuser\\data\\header\\hash=7a\\timeKey=2023052409\\vin=LVGD31354NG002996\\part-00000-a44308b2-dea2-4ce8-9bf4-7bd49896307e.c000.snappy.parquet")
//			.show(500,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveHeader(Dataset<Row> dsr) {
		dsr.select(col("vin"), col("dispatchModelType"), col("requestDateTime"))
				.select(col("*"), row_number().over(Window.partitionBy("vin").orderBy("vin")).as("rowNumber"))
				.filter(col("rowNumber").equalTo(1))
				.select(col("vin"), col("dispatchModelType"),
						md5(col("vin")).substr(length(md5(col("vin"))).minus(1), lit(2)).as("hash"),
						date_format_long.apply(col("requestDateTime"), lit("yyyyMMddHH")).as("timeKey"))
				.repartition(col("hash"), col("timeKey"), col("vin")).write().mode(SaveMode.Append)
				.partitionBy("hash", "timeKey", "vin").parquet("D:\\home\\apuser\\data\\header");

	}
	
	
	public void readByA2Style(SparkSession spark) {
		Dataset<Row> rst = spark.read().format("avro")
		.load("D:\\home\\apuser\\data\\dowload\\19dcm\\procdata\\CN\\real\\can_External\\00\\2023\\05\\*\\*\\*.avro")
		.filter(col("headers").isNotNull())
        .filter((FilterFunction<Row>)r -> {
            Map<CharSequence, CharSequence> headers = r.getJavaMap(0);
            CharSequence vin = headers.get("VIN");
            CharSequence requestDateTime = headers.get("RequestDateTime");
            
            if (vin == null || requestDateTime == null) {
            	System.out.println("requestDateTime == null ");
                return false;
            }
            
            try {
                toLongTime.apply(requestDateTime.toString(), "request");
            } catch (Exception e) {
                return false;
            }
            
            return true;
            
        })
        .select(col("headers.VIN").as("vin"),
                col("headers.DispatchModelType").as("dispatchModelType"),
                datetime_to_long.apply(col("headers.RequestDateTime"), lit("request")).as("requestDateTime"),
                
                col("body.dataCapacityList").as("dcList"))
        .withColumn("timeKey", date_format_long.apply(col("requestDateTime"), lit("yyyyMMddHH")));
		showByHeader(rst);

	}
	public void showByHeader(Dataset<Row> dsr) {
		dsr.select(col("vin"), col("dispatchModelType"), col("requestDateTime"))
				.select(col("*"), row_number().over(Window.partitionBy("vin").orderBy("vin")).as("rowNumber"))
				.filter(col("rowNumber").equalTo(1))
				.select(col("vin"), col("dispatchModelType"),
						md5(col("vin")).substr(length(md5(col("vin"))).minus(1), lit(2)).as("hash"),
						date_format_long.apply(col("requestDateTime"), lit("yyyyMMddHH")).as("timeKey"))
				.repartition(col("hash"), col("timeKey"), col("vin"))
				.show(500,false);
	}
	
	public void saveOutSide(Dataset<Row> dsr) {
		dsr.select(col("vin"), explode(col("dcList")).as("dc"))
				.select(col("vin"), explode(col("dc.canInformationList")).as("can")).filter((FilterFunction<Row>) r -> {
					CharSequence time = r.getStruct(1).getAs("time");
					if (time == null) {
						return false;
					}
					try {
						toLongTime.apply(time.toString(), "outside");
					} catch (Exception e) {
						return false;
					}
					return true;
				})
				.select(col("vin"), datetime_to_long.apply(col("can.time"), lit("outside")).as("time"),
						col("can.outsideUseDataMap"))
				.select(col("vin"), col("time"), explode(col("outsideUseDataMap")))
				.select(col("vin"), col("time"), col("key").as("label"), col("value"))
				.filter(lit(requiredLabels).equalTo("").or(col("label").isin((Object[]) requiredLabels.split(","))))
				.select(col("vin"), col("time"), col("label"), explode(toMapKeyOnly.apply(col("value"))))
				.select(col("vin"), col("time"), col("label"), col("key").as("value"),
						md5(col("vin")).substr(length(md5(col("vin"))).minus(1), lit(2)).as("hash"),
						date_format_long.apply(col("time"), lit("yyyyMMddHH")).as("timeKey"))
				.repartition(col("hash"), col("timeKey"), col("vin")).write().mode(SaveMode.Append)
				.partitionBy("hash", "timeKey", "vin").parquet("D:\\home\\apuser\\data\\outside");
	}

	public void saveType3(Dataset<Row> dsr) {
		dsr.select(col("vin"), explode(col("dcList")).as("dc"))
				.select(col("vin"), explode(col("dc.type3OutsideUseData")).as("t")).filter((FilterFunction<Row>) r -> {

					CharSequence dateTime = r.getStruct(1).getAs("dateTime");
					if (dateTime == null) {
						System.err.println("LACK_OF_KEY dateTime");
						return false;
					}

					try {
						toLongTime.apply(dateTime.toString(), "type3");
					} catch (Exception e) {
						System.err.println("INVALID_DATA dateTime=" + dateTime);
						return false;
					}

					if (r.getStruct(1).getString(1) == null) {
						System.err.println("LACK_OF_KEY label");
						return false;
					}

					return true;

				})
				.select(col("vin"), datetime_to_long.apply(col("t.dateTime"), lit("type3")).as("dateTime"),
						col("t.label"), col("t.value"))
				.filter(lit(requiredLabels).equalTo("").or(col("label").isin((Object[]) requiredLabels.split(","))))
				.select(col("*"), md5(col("vin")).substr(length(md5(col("vin"))).minus(1), lit(2)).as("hash"),
						date_format_long.apply(col("dateTime"), lit("yyyyMMddHH")).as("timeKey"))
				.repartition(col("hash"), col("timeKey"), col("vin")).write().mode(SaveMode.Append)
				.partitionBy("hash", "timeKey", "vin").parquet("D:\\home\\apuser\\data\\type3");
	}

	public void showHeader(SparkSession spark,String url) {
		Dataset<Row> DS_Header = spark.read().option("basePath", "D:\\home\\apuser\\data\\header\\").parquet(url);
		DS_Header.show(500,false);
	}

	public void showType3(SparkSession spark,String url) {
		Dataset<Row> DS_Type3 = spark.read().option("basePath", "D:\\home\\apuser\\data\\type3\\").parquet(url);
		DS_Type3.show(500,false);
	}

	public void showOutside(SparkSession spark,String url) {
		Dataset<Row> DS_OutSide = spark.read().option("basePath", "D:\\home\\apuser\\data\\outside\\").parquet(url);
		DS_OutSide.show(500,false);
	}
}
