package zdf.learn.com.commonUtils.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.sql.Row;

import zdf.learn.com.commonUtils.pojo.MonthTempData;
import zdf.learn.com.commonUtils.pojo.TripCanStrPojo;
import zdf.learn.com.commonUtils.pojo.TripCanVelocityTimePojo;
import zdf.learn.com.commonUtils.pojo.TripRemoteStrPojo;



public class HcrUtil {
    
    
  /**
   * 创建分配边界列表
   * 
   * @param targetStart
   * @param targetEnd
   * @return
   */
  private static List<LocalDateTime> makeLimitList(LocalDateTime targetStart,
      LocalDateTime targetEnd) {

    List<LocalDateTime> rs = new ArrayList<>();
    LocalDate currentDate = targetStart.toLocalDate();

    // 考虑到targetStart有可能在今天5点之前，从前一天的18点开始统计
    rs.add(currentDate.minusDays(1).atTime(18, 0));

    do {
      rs.add(currentDate.atTime(5, 0));
      rs.add(currentDate.atTime(9, 0));
      rs.add(currentDate.atTime(18, 0));

      currentDate = currentDate.plusDays(1);
    } while (!currentDate.isAfter(targetEnd.toLocalDate()));

    // 考虑到targetEnd有可能在今天18点之后，统计到次日5点。
    rs.add(currentDate.atTime(5, 0));

    return rs;
  }
    
  /**
   * 分段获取时间
   * 
   * @param targetStart
   * @param targetEnd
   * @return
   */
  public static int[] distributeTime(LocalDateTime targetStart, LocalDateTime targetEnd) {

    // 创建分配边界列表
    List<LocalDateTime> limitList = makeLimitList(targetStart, targetEnd);

    // 分配时间
    int[] rs = new int[3];
    for (int i = 0; i < limitList.size() - 1; i++) {
      rs[i % 3] +=
          distributeTime(limitList.get(i), limitList.get(i + 1), targetStart, targetEnd);
    }

    return rs;
  }

    /**
     * 分段获取时间
     * @param limitStart 分配边界开始，例如05:00
     * @param limitEnd 分配边界结束，例如09:00
     * @param targetStart
     * @param targetEnd
     * @return
     */
    private static long distributeTime(LocalDateTime limitStart, LocalDateTime limitEnd, LocalDateTime targetStart, LocalDateTime targetEnd) {
        
        if(limitEnd.isBefore(targetStart) || limitStart.isAfter(targetEnd)) {
            return 0;
        }
        
        long limitStartS = limitStart.toEpochSecond(ZoneOffset.of("+8"));
        long limitEndS = limitEnd.toEpochSecond(ZoneOffset.of("+8"));

        long base = limitEndS - limitStartS;
        long gapStart = targetStart.toEpochSecond(ZoneOffset.of("+8")) - limitStartS;
        long gapEnd = limitEndS - targetEnd.toEpochSecond(ZoneOffset.of("+8"));
        
        gapStart = gapStart > 0 ? gapStart : 0;
        gapEnd = gapEnd > 0 ? gapEnd : 0;
        
        long rs = base - gapStart - gapEnd;
        
        return rs > 0 ? rs : 0;
    }
    
    /**
     * 按范围取值
     * @param values
     * @param limits 例：String[]{"2次", "10次", "20次"}
     * @return
     */
    public static int[] distributeValue(Collection<Integer> values, int[] limits) {
        int[] rs = new int[limits.length + 2];
        
        for (int value : values) {
            boolean isHit = false;
            for (int i = 0; i < limits.length; i++) {
                if (value <= limits[i]) {
                    rs[i + 1]++;
                    isHit = true;
                    break;
                }
            }
            
            if (!isHit)
                rs[limits.length + 1]++;
        }
        
        return rs;
    }
    
    /**
     * 获取配置文件
     * @return
     */
    public static Properties  getProperties() {
        Properties prop = new Properties();
        try {
            String property = System.getProperty("os.name");
//            if (property.startsWith("Windows")) {
//                prop.load(new FileInputStream(System.getProperty("user.dir") + "/toyota-hcr-spark/mysql.properties"));
//                prop.load(new FileInputStream(System.getProperty("user.dir") + "/toyota-hcr-spark/performance.properties"));
//                prop.load(new InputStreamReader(new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "/toyota-hcr-spark/businessConstant.properties")), "utf-8"));
//            } else {
//                prop.load(new FileInputStream(System.getProperty("user.dir") + "/mysql.properties"));
//                prop.load(new FileInputStream(System.getProperty("user.dir") + "/performance.properties"));
//                prop.load(new InputStreamReader(new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "/businessConstant.properties")), "utf-8"));
//            }
            prop.load(new FileInputStream("mysql.properties"));
            prop.load(new FileInputStream("performance.properties"));
            prop.load(new InputStreamReader(new BufferedInputStream(new FileInputStream("businessConstant.properties")),"utf-8"));
        } catch (IOException e) {
            return null;
        }
        return prop;
    }
    

   
    
    /**
	 * 行转对象
	 * @param r
	 * @return
	 */
	public static TripCanStrPojo rowParseTo(Row r) {
		Timestamp igOn = r.getTimestamp(1);
		Timestamp igOff = r.getTimestamp(2);
		String type = r.getString(19);
		TripCanStrPojo trip = new TripCanStrPojo();
		trip.setTargetDate(r.getDate(0).toLocalDate().toString());
		trip.setIgOn(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(igOn.toInstant(), ZoneOffset.UTC)));
		trip.setIgOff(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(igOff.toInstant(), ZoneOffset.UTC)));
		trip.setDrivingTime(r.getInt(3));
		trip.setFuelEfficiency(r.getDouble(4));
		trip.setMaxThrottleOpenDegree((float) r.getDouble(5));
		trip.setTotalThrottleOpenDegree(r.getDouble(6));
		trip.setThrottleDataRecords( r.getInt(7));
		trip.setSuddenBrakeTimes(r.getInt(8));
		trip.setEcoModeTime( r.getInt(9));
		trip.setNormalModeTime(r.getInt(10));
		trip.setSportModeTime( r.getInt(11));
		trip.setPowerModeTime( r.getInt(12));
		trip.setSnowModeTime( r.getInt(13));
		trip.setInnerCirculationTime( r.getInt(14));
		trip.setOuterCirculationTime( r.getInt(15));
		trip.setWiperUseTime( r.getInt(16));
		trip.setOdoTrip(r.getInt(17));
		trip.setOdoLatest(r.getInt(18));
		trip.setType(type);
		trip.setAirConditioningUseTime(r.getInt(20));
		trip.setMaxSpeed(r.getInt(21));
		trip.setAssistDataTime(r.getInt(22)+"");
		trip.setVehicleId(r.getString(23));
		trip.setCreatetime("2023-07-12 00:10:20");
		return trip;
	}
	/**
	 * 行转对象
	 * @param r
	 * @return
	 */
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

	public static TripRemoteStrPojo rowToRemote(Row r){
		TripRemoteStrPojo remote = new TripRemoteStrPojo();
		remote.setVehicleId(r.getString(2));
		remote.setMonitoringTime(r.getString(3));
		remote.setSettingValue(r.getString(4));
		remote.setCreatetime(r.getString(5));
		return remote;
	}
	
	public static TripCanVelocityTimePojo rowToVelocityTime(Row r){
		TripCanVelocityTimePojo velocity = new TripCanVelocityTimePojo();
		velocity.setId(r.getLong(1));
		velocity.setVehicleId(r.getString(2));
		velocity.setVelocityTime(r.getString(3));
		 
		return velocity;
	}
	/**
	 * 获取月的第几周
     * @param targetDay :yyyyMMdd
     * 头部不满周的，按第0周计算
	 * @return
	 */
	public static Integer getWeekOfMonth(String targetDay) {
		LocalDate targetDt = LocalDate.parse(targetDay, DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		Integer targetDayInt = Integer.parseInt(targetDay);
		int returnWeek = 0;
		boolean isNext = true;
		while(isNext) {
			List<String> weekList = getDaysInWeek(Integer.parseInt(targetDay.substring(0, 4)),
					Integer.parseInt(targetDay.substring(4, 6)),
					returnWeek+1);
			if(weekList.isEmpty()) {
				isNext = false;
			}else {
				
				if(targetDayInt>=Integer.parseInt(weekList.get(0)) 
						&& targetDayInt<=Integer.parseInt(weekList.get(weekList.size()-1))) {
					return returnWeek;
				}else {
					returnWeek ++;
				}
			}
		}
		
		return returnWeek;
	}
	/**
	 * 获取指定周的日期队列
	 * @param targetYear
	 * @param targetMonth
     * @param weekIndex 指定年月第x周  0~5
	 * @return
	 */
    public static List<String> getDaysInWeek(int targetYear , int targetMonth , int weekIndex ){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	List<String> returnTargetDate = new ArrayList<String>();
    	LocalDate TargetDt = LocalDate.parse(targetYear*10000+targetMonth*100 +1 +"", DateTimeFormatter.ofPattern("yyyyMMdd"));
    	int nowMonthValue = TargetDt.getMonthValue();
    	LocalDate firstDayOfMonth = TargetDt.with(TemporalAdjusters.firstDayOfMonth());
    	LocalDate nextDate        = TargetDt.with(TemporalAdjusters.firstDayOfMonth());
    	int weekCnt = 0;
    	for(int day = 0 ; day <32 ; day ++) {
    		nextDate = firstDayOfMonth.plusDays(day);
    		if(nextDate.getMonthValue() == nowMonthValue) {
    			if(weekCnt == weekIndex) {
    				returnTargetDate.add(dtf.format(nextDate));
    			}
    			if(nextDate.getDayOfWeek().getValue()==7) {
    				weekCnt ++;
    			}
    		}
    		
    	}
    	return returnTargetDate;
    }
    
    public static List<String> getDaysInWeekIgnoreMonth(int targetYear , int targetMonth , int weekIndex ){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	String[] dataRange = new String[7];
    	LocalDate TargetDt = LocalDate.parse(targetYear*10000+targetMonth*100 +1 +"", DateTimeFormatter.ofPattern("yyyyMMdd"));
    	int tarWeek = TargetDt.getDayOfWeek().getValue();
    	LocalDate firstDayOfMonth = TargetDt.with(TemporalAdjusters.firstDayOfMonth());
    	LocalDate nextDate        = TargetDt.with(TemporalAdjusters.firstDayOfMonth());
    	int weekCnt = 0;
    	for(int day = 0 ; day <32 ; day ++) {
    		nextDate = firstDayOfMonth.plusDays(day);
    		if(nextDate.getDayOfWeek().getValue() == 7 && weekIndex == weekCnt ) {
    			System.out.println(nextDate+","+weekIndex);
    			for(int j = 1 ; j <= 7 ; j ++) {
    				dataRange[j-1] = dtf.format(nextDate.minusDays(7-j));
    			}
    			break;
    		}else if(nextDate.getDayOfWeek().getValue()==7){
    			System.out.println("next week");
    			weekCnt ++;
    		}
    	}
    	return  Arrays.asList(dataRange);
    }
    /**
     * 获取指定月的日期队列
     * @param monthValue 第x月 1~12 
     * @return
     */
    public static List<String> getDaysInMonth(int yearValue ,int monthValue){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	List<String> returnTargetDate = new ArrayList<String>();
    	LocalDate nowDt = LocalDate.now(ZoneId.of("Asia/Shanghai"));
    	int maxCircle = 1000;
    	while(nowDt.getYear() != yearValue && maxCircle>0) {
    		if(yearValue>nowDt.getYear()){
    			nowDt = nowDt.plusYears(1);
    		}else if(yearValue<nowDt.getYear()){
    			nowDt = nowDt.minusYears(1);
    		}
    		maxCircle --;
    	}
    	
    	while(nowDt.getMonthValue() != monthValue && maxCircle>0) {
    		if(monthValue>nowDt.getMonthValue()){
    			nowDt = nowDt.plusMonths(1);
    		}else if(monthValue<nowDt.getMonthValue()){
    			nowDt = nowDt.minusMonths(1);
    		}
    		maxCircle --;
    	}
    	LocalDate firstDayOfMonth = nowDt.with(TemporalAdjusters.firstDayOfMonth());
    	LocalDate nextDate = nowDt.with(TemporalAdjusters.firstDayOfMonth());
    	
    	
    	while(firstDayOfMonth.getMonthValue() == nextDate.getMonthValue() && maxCircle>0) {
    		returnTargetDate.add(dtf.format(nextDate));
    		nextDate = nextDate.plusDays(1);
    		maxCircle --;
    	}
    	System.out.println("maxCircle:::"+maxCircle);
    	if(maxCircle<=0) {
    		System.out.println("maxCircle:::"+maxCircle);
    	}
    	return returnTargetDate;
    }
    /**
     * 
     * @param targetDay
     * @return
     */
    public static String getFirstDayOfWeek(String targetDay) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	LocalDate firstDay = LocalDate.parse(targetDay, dtf).with(TemporalAdjusters.firstDayOfMonth());
    	return firstDay.format(dtf);
    }
    /**
     * 
     * @param targetDay
     * @return
     */
    public static String getEndDayOfMonth(String targetDay) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	LocalDate firstDay = LocalDate.parse(targetDay, dtf).with(TemporalAdjusters.firstDayOfMonth());
    	LocalDate nextMonthFirstDay =firstDay.plusMonths(1);
    	LocalDate lastDay = nextMonthFirstDay.minusDays(1);
    	return lastDay.format(dtf);
    }
    /**
     * 不跨月取一周
     * @param targetDay
     * @return
     */
    public static String getEndDayOfWeekFull(String targetDay) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    	LocalDate firstDay = LocalDate.parse(targetDay, dtf).with(TemporalAdjusters.firstDayOfMonth());
    	LocalDate nextMonthFirstDay =firstDay.plusMonths(1);
    	LocalDate lastDay = nextMonthFirstDay.minusDays(1);
    	return lastDay.format(dtf);
    }
    //.toString()
    /**
     * 字符串转ZonedDateTime
     * @param strDate
     * @return
     */
    public static ZonedDateTime stringToZonedDateTime(String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai"));
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate, formatter);
        return zonedDateTime;
    }
    
    /**
     * 字符串转ZonedDateTime
     * @param strDate
     * @return
     */
    public static LocalDate stringToLocalDate(String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.of("Asia/Shanghai"));
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate, formatter);
        return zonedDateTime.toLocalDate();
    }
    /**
     * 
     * @param frequencyArr  title
     * @param drivingFrequency title对应的值
     */
    public static void saveDriveRatePerWeek(String[] frequencyArr,int[] drivingFrequency) {
    	List<Integer> drivingDayPerFrequencyList = new ArrayList<Integer>();
    	for (int i = 0; i < frequencyArr.length; i++) {
            drivingDayPerFrequencyList.add(drivingFrequency[i]);
        }
    }
    
    private static final DateTimeFormatter PATTERN_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * 获取Range的最后一天
     * @param targetDay
     * @return
     */
    public static LocalDate getEndDayOfRange(List<String> targetDayRange) {
    	if(targetDayRange!=null && targetDayRange.size()>0) {
    		String targetDay = targetDayRange.get(targetDayRange.size()-1);
    		LocalDate rs = LocalDate.parse(targetDay, PATTERN_YYYYMMDD);
    		return rs.plusDays(1);
    	}else {
    		return null;
    	}
    	
    }
    /**
     * 获取周的最后一天
     * @param targetDay
     * @return
     */
    public static LocalDate getEndDayOfWeek(String targetDay) {
		LocalDate rs = LocalDate.parse(targetDay, PATTERN_YYYYMMDD);
		return rs.plusDays(7 - rs.getDayOfWeek().getValue());
    }
    public static  List<Integer>  ArrayToList(int[] args) {
    	List<Integer> ret = new ArrayList<Integer>();
    	for(int s : args) {
    		ret.add(s);
    	}
    	return ret;
    }
    /**
     * 逐位合并队列
     * @param left
     * @param right
     * @return
     */
    public static List<Integer> listCombine(List<Integer> left,List<Integer> right) {
    	for(int i = 0 ; i < Math.max(left.size(), right.size()); i++) {
    		if(i<left.size() && i < right.size()) {
    			left.set(i, left.get(i)+right.get(i));
    		}else if(i<left.size() && i >= right.size()) {
    			left.set(i, left.get(i)+0);
    		}
    		else if(i>=left.size() && i < right.size()) {
    			left.add(right.get(i));
    		}
    	}
    	return left;
    	
    }
    public static LocalDate dateStrToLocalDate(String date,String dateFormat) {
    	return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }
    public static LocalDateTime dateStrToLocalDateTime(String date,String dateFormat) {
    	return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }
   
}
