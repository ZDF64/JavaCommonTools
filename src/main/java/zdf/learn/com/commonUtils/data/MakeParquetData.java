package zdf.learn.com.commonUtils.data;
import static org.apache.spark.sql.functions.array_max;
import static org.apache.spark.sql.functions.broadcast;
import static org.apache.spark.sql.functions.ceil;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.collect_list;
import static org.apache.spark.sql.functions.concat_ws;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.explode;
import static org.apache.spark.sql.functions.first;
import static org.apache.spark.sql.functions.lag;
import static org.apache.spark.sql.functions.last;
import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.map;
import static org.apache.spark.sql.functions.not;
import static org.apache.spark.sql.functions.round;
import static org.apache.spark.sql.functions.row_number;
import static org.apache.spark.sql.functions.size;
import static org.apache.spark.sql.functions.split;
import static org.apache.spark.sql.functions.sum;
import static org.apache.spark.sql.functions.when;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;

public class MakeParquetData {
	// label
    private static Object[] SPEED;
    private static Object[] FUEL_CONSUMPTION;
    private static Object[] THROTTLE_DEPTH;
    private static Object[] BRAKES_NUMBER;
    private static Object[] ECO_PATTERN;
    private static Object[] SPORTS_PATTERN;
    private static Object[] POWER_PATTERN;
    private static Object[] SNOW_PATTERN;
    private static Object[] INNER_OUTER_CIRCULATION;
    private static Object[] WIPER;
    private static Object[] RUNNING_DISTANCE;
    private static Object[] AIR_CONDITIONER;

    private static Object[] WIPER_ON_IDENTIFIER;
    private static Object[] WIPER_OFF_IDENTIFIER;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		 
        try (SparkSession spark = SparkSession.builder()
				.appName(MakeParquetData
						.class.getSimpleName() + " - " + new Date())
				.master("local[6]").getOrCreate();
				JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());){
			prop.load(MakeParquetData.class.getResourceAsStream("/can-e-spark-config"));
			MakeParquetData mpd = new MakeParquetData();
			mpd.makeBy_E_Method(spark, prop);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void makeBy_E_Method(SparkSession spark,Properties prop) {
		SPEED = prop.getProperty("create-trip.label.speed").split(",");
        FUEL_CONSUMPTION = prop.getProperty("create-trip.label.fuel_consumption").split(",");
        THROTTLE_DEPTH = prop.getProperty("create-trip.label.throttle_depth").split(",");
        BRAKES_NUMBER = prop.getProperty("create-trip.label.brakes_number").split(",");
        ECO_PATTERN = prop.getProperty("create-trip.label.eco_pattern").split(",");
        SPORTS_PATTERN = prop.getProperty("create-trip.label.sports_pattern").split(",");
        POWER_PATTERN = prop.getProperty("create-trip.label.power_pattern").split(",");
        SNOW_PATTERN = prop.getProperty("create-trip.label.snow_pattern").split(",");
        INNER_OUTER_CIRCULATION = prop.getProperty("create-trip.label.inner_outer_circulation").split(",");
        WIPER = prop.getProperty("create-trip.label.wiper").split(",");
        RUNNING_DISTANCE = prop.getProperty("create-trip.label.running_distance").split(",");
        AIR_CONDITIONER = prop.getProperty("create-trip.label.air_conditioner").split(",");

		 // 油耗数据
        String[] fuelSection = prop.getProperty("create-trip.label.fuel_consumption.effective_range").split(",");
        // 各模式处理
        Object[] operationModeSection = prop.getProperty("create-trip.label.operation_mode.effective_range").split(",");
        // 油门开度区间
        String[] throttleSection = prop.getProperty("create-trip.label.throttle_depth.effective_range").split(",");
        // 速度区间
        String[] speedSection = prop.getProperty("create-trip.label.speed.effective_range").split(",");
        // 内外循环时间区间区间
        Object[] innerOuterSection = prop.getProperty("create-trip.label.inner_outer_circulation.effective_range").split(",");
        // 雨刷区间
        Object[] wiperSection = prop.getProperty("create-trip.label.wiper.effective_range").split(",");
        // 空调使用时间区间
        Object[] airSection = prop.getProperty("create-trip.label.air_conditioner.effective_range").split(",");
        // 行驶距离区间
        String[] runningSection = prop.getProperty("create-trip.label.running_distance.effective_range").split(",");

        
		
		Dataset<Row> outside = spark.read().option("basePath", "")
                .parquet("")
                .filter((col("label").isin(FUEL_CONSUMPTION).and(col("value").between(Double.valueOf(fuelSection[0]), Double.valueOf(fuelSection[1]))))
                        .or(col("label").isin(ECO_PATTERN).and(col("value").cast(DataTypes.IntegerType).isin(operationModeSection)))
                        .or(col("label").isin(SPORTS_PATTERN).and(col("value").cast(DataTypes.IntegerType).isin(operationModeSection)))
                        .or(col("label").isin(SNOW_PATTERN).and(col("value").cast(DataTypes.IntegerType).isin(operationModeSection)))
                        .or(col("label").isin(POWER_PATTERN).and(col("value").cast(DataTypes.IntegerType).isin(operationModeSection)))
                        .or(col("label").isin(THROTTLE_DEPTH).and(col("value").between(Double.valueOf(throttleSection[0]), Double.valueOf(throttleSection[1]))))
                        .or(col("label").isin(SPEED).and(col("value").between(Double.valueOf(speedSection[0]), Double.valueOf(speedSection[1]))))
                        .or(col("label").isin(INNER_OUTER_CIRCULATION).and(col("value").cast(DataTypes.IntegerType).isin(innerOuterSection)))
                        .or(col("label").isin(WIPER).and(col("value").cast(DataTypes.IntegerType).isin(wiperSection)))
                        .or(col("label").isin(AIR_CONDITIONER).and(col("value").cast(DataTypes.IntegerType).isin(airSection)))
                        .or(col("label").isin(RUNNING_DISTANCE).and(col("value").between(Double.valueOf(runningSection[0]), Double.valueOf(runningSection[1])))))
                .select(col("vin"), col("label"), when(col("label").isin(WIPER).and(col("value").cast(DataTypes.IntegerType).isin(WIPER_ON_IDENTIFIER)), 1)
                        .when(col("label").isin(WIPER).and(col("value").cast(DataTypes.IntegerType).isin(WIPER_OFF_IDENTIFIER)), 0)
                        .otherwise(col("value")).as("value"), col("time"))
                .cache();
	}
}
