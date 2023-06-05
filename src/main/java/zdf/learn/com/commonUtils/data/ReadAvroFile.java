package zdf.learn.com.commonUtils.data;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.explode;
import static org.apache.spark.sql.functions.length;
import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.md5;
import static zdf.learn.com.commonUtils.data.functions.date_format_long;
import static zdf.learn.com.commonUtils.data.functions.datetime_to_long;
import static zdf.learn.com.commonUtils.data.functions.toLongTime;
import static zdf.learn.com.commonUtils.data.functions.toMapKeyOnly;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.obs.services.ObsClient;

public class ReadAvroFile {
	static String requiredLabels = "Speed,Speed_TypeA,Speed_TypeB,FuelConsumption,AccelPedalAngle_TypeA,AccelPedalAngle_TypeC,AccelPedalAngle_TypeB,AccelerationFB,AccelerationFB_TypeB,AccelerationFB_TypeC,EcoModeIndicator,DriveModeECO,DriveModeECO_TypeB,SportModeSelect,DriveModeSPORT,DriveModeSPORT_TypeB,PowerModeSelect_TypeA,PowerModeSelect_TypeB,DriveModePOWER,DriveModePOWER_TypeB,SnowModeSelect,RecIndicator,WiperControl,Odometer_km,AirConIndicator";

	public static void ReadAvroFileByPath(String filePath) {
		try {
			InputStream in = new FileInputStream(filePath);
			DataFileStream<Object> dfs = new DataFileStream<>(in, new GenericDatumReader<>());
			String schemaJson = dfs.getSchema().toString();
			System.out.println(schemaJson);
//			DataFileReader reader = new DataFileReader<>(new File(""), null) ;
//			reader.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readAvroFromS3ByMultiThread(String ak,String sk,String bucket,String path) {
		
	}
	public static void readAvroFromObs(String ak,String sk,String bucket,String path) {
		
	}
	
	public static void main(String[] args) {
		ObsClient obsPartition = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");

		try (SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date()).master("local[6]").getOrCreate();
				JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
				) {
			
			Dataset<Row> readAvro = spark.read().format("avro").load("D:\\home\\apuser\\parquet\\a\\procdata\\CN\\real\\can_External\\*\\2023\\06\\01\\16")
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
                    col("body.dataCapacityList").as("dcList"));
			
			readAvro.select(col("vin"), explode(col("dcList")).as("dc"))
	        .select(col("vin"), explode(col("dc.canInformationList")).as("can"))
	        .select(col("vin")).distinct()
//	        .filter((FilterFunction<Row>)r -> {
//	            CharSequence time = r.getStruct(1).getAs("time");
//	            if (time == null) {
//	            	System.err.println("MessageType.LACK_OF_KEY");
//	                return false;
//	            }
//	            try {
//	                toLongTime.apply(time.toString(), "outside");
//	            } catch (Exception e) {
//	            	System.err.println("MessageType.INVALID_DATA,time=" + time);
//	                return false;
//	            }
//	            return true;
//	        })
//	        .select(col("vin"), datetime_to_long.apply(col("can.time"), lit("outside")).as("time"), col("can.outsideUseDataMap"))
//	        .select(col("vin"), col("time"), explode(col("outsideUseDataMap")))
//	        .select(col("vin"), col("time"), col("key").as("label"), col("value"))
//	        .filter(lit(requiredLabels).equalTo("").or(col("label").isin((Object[]) requiredLabels.split(","))))
//	        .select(col("vin"), col("time"), col("label"), explode(toMapKeyOnly.apply(col("value"))))
//	        .select(col("vin"), col("time"), col("label"), col("key").as("value"),
//	                md5(col("vin")).substr(length(md5(col("vin"))).minus(1), lit(2)).as("hash"),
//	                date_format_long.apply(col("time"), lit("yyyyMMddHH")).as("timeKey"))
//	        .repartition(col("hash"), col("timeKey"), col("vin"))
	        .show(500,false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		ReadAvroFile.ReadAvroFileByPath("D:\\home\\apuser\\AvroData\\_data.avro");
//		File newFile = new File("\\home\\apuser\\avroData\\procdata\\CN\\real\\can_External\\08\\2023\\05\\07\\08\\");
//		newFile.mkdirs();
	}
}
