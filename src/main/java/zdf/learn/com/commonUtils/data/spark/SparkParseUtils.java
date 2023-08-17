package zdf.learn.com.commonUtils.data.spark;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.size;
import static org.apache.spark.sql.functions.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Row;

import zdf.learn.com.commonUtils.pojo.MonthTempData;
import zdf.learn.com.commonUtils.pojo.TripCanStrPojo;

public class SparkParseUtils {
	public static TripCanStrPojo rowParseTo(Row r) throws ParseException {
		
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
		trip.setEcoModeTime(r.getInt(9));
		trip.setNormalModeTime(r.getInt(10));
		trip.setSportModeTime(r.getInt(11));
		trip.setPowerModeTime(r.getInt(12));
		trip.setSnowModeTime(r.getInt(13));
		trip.setInnerCirculationTime(r.getInt(14));
		trip.setOuterCirculationTime(r.getInt(15));
		trip.setWiperUseTime(r.getInt(16));
		trip.setOdoTrip(r.getInt(17));
		trip.setOdoLatest(r.getInt(18));
		trip.setType(type);
		trip.setAirConditioningUseTime(r.getInt(20));
		trip.setMaxSpeed(r.getInt(21));
		trip.setAssistDataTime(r.getString(22) + "");
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


	public static Column getNotEmptyListCol(Object[] labels) {
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

	
	@SafeVarargs
	public static <T> T[] concatAll(T[] first, T[]... rest) {
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
