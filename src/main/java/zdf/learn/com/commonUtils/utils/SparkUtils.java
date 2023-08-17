package zdf.learn.com.commonUtils.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import zdf.learn.com.commonUtils.pojo.AirConditioningUseTimeInf;
import zdf.learn.com.commonUtils.pojo.AirConditioningUseTimeInf.AirConditioningUseTimeInfBuilder;
import zdf.learn.com.commonUtils.pojo.CirculationDestributionInf;
import zdf.learn.com.commonUtils.pojo.CirculationDestributionInf.CirculationDestributionInfBuilder;
import zdf.learn.com.commonUtils.pojo.CustomerAppMonthInf;
import zdf.learn.com.commonUtils.pojo.CustomerAppMonthInf.CustomerAppMonthInfBuilder;
import zdf.learn.com.commonUtils.pojo.DrivingDayPerFrequencyInf;
import zdf.learn.com.commonUtils.pojo.DrivingDayPerFrequencyInf.DrivingDayPerFrequencyInfBuilder;
import zdf.learn.com.commonUtils.pojo.DrivingDayPerMilageInf;
import zdf.learn.com.commonUtils.pojo.DrivingDayPerMilageInf.DrivingDayPerMilageInfBuilder;
import zdf.learn.com.commonUtils.pojo.DrivingTimezoneInf;
import zdf.learn.com.commonUtils.pojo.DrivingTimezoneInf.DrivingTimezoneInfBuilder;
import zdf.learn.com.commonUtils.pojo.DrvDayPerDrivingTimeInf;
import zdf.learn.com.commonUtils.pojo.DrvDayPerDrivingTimeInf.DrvDayPerDrivingTimeInfBuilder;
import zdf.learn.com.commonUtils.pojo.MilageDayPerMonthInf;
import zdf.learn.com.commonUtils.pojo.MilageDayPerMonthInf.MilageDayPerMonthInfBuilder;
import zdf.learn.com.commonUtils.pojo.MonthTempData;
import zdf.learn.com.commonUtils.pojo.PowerSportUseTimeInf;
import zdf.learn.com.commonUtils.pojo.PowerSportUseTimeInf.PowerSportUseTimeInfBuilder;
import zdf.learn.com.commonUtils.pojo.RmtSrvMilagePerMonthInf;
import zdf.learn.com.commonUtils.pojo.RmtSrvMilagePerMonthInf.RmtSrvMilagePerMonthInfBuilder;
import zdf.learn.com.commonUtils.pojo.SuddenBrakeTimesInf;
import zdf.learn.com.commonUtils.pojo.SuddenBrakeTimesInf.SuddenBrakeTimesInfBuilder;
import zdf.learn.com.commonUtils.pojo.TripCanStrPojo;
import zdf.learn.com.commonUtils.pojo.WiperUseTimeInf;
import zdf.learn.com.commonUtils.pojo.WiperUseTimeInf.WiperUseTimeInfBuilder;


public class SparkUtils {
	private static final String TASK_NAME = "CanDataWeekly";
    private static final int[] drivingFrequencyArr = new int[] { 2, 5, 10, 20 };
    private static final int[] drivingTimeArr = new int[] { 1800, 3600, 3600 * 2, 3600 * 3 };
    private static final int[] odoTripArr = new int[] { 10, 30, 100, 200 };
    
    
    
    /**
     * 将给定的tripCanList生成temp文件
     * @param tripCanList
     * @param DateRange
     * @param targetWeek
     * @param targetMonth      
     * @param weekTemp         将结果存入此队列
     * @throws Exception
     */
	public static void processDataSaveTemp(List<TripCanStrPojo> tripCanList
			, List<String> DateRange
			, Integer targetWeek,
			Integer targetMonth, 
			List<MonthTempData> weekTemp)
			throws Exception {
		System.out.println("start to compute temp Data");
		Map<String, List<TripCanStrPojo>> map = new HashMap<String, List<TripCanStrPojo>>();
		map = tripCanList.stream().collect(Collectors.groupingBy(TripCanStrPojo::getVehicleId));
        LocalDate endLocalDate = HcrUtil.getEndDayOfRange(DateRange);
        for (Entry<String, List<TripCanStrPojo>> entry : map.entrySet()) {
        	int totalMilage = 0;
        	int drivingTimeCnt = 0;
        	int wiperTimeCnt = 0;
        	int airTimeCnt = 0;
        	int innerCnt = 0;
        	int outerCnt = 0;
        	int modeTime = 0;
        	int suddenBrakeCnt = 0;
        	int maxOdoLatest = 0;
        	int maxSpeed = 0;
        	int totaleffectiveDrivingDuration = 0;
        	int totalDrivingDuration = 0;
        	int totalDrivingTimes = 0;
        	Double fuelEfficiency = 0.0;
        	MonthTempData vehicleTempData = new MonthTempData();
            int odoTripCnt = 0;
            double fuelCnt = 0;
            float maxOpenDegree = 0;
            double openDegreeCnt = 0;
            int recordsCnt = 0;
            String type = "";
            
            Map<LocalDate, Integer> dateAndFrequencyMap = new HashMap<>();
            Map<LocalDate, Integer> odoTripMap = new HashMap<>();
            Map<LocalDate, Integer> drivingTimeMap = new HashMap<>();
            int[] drivingTimes = new int[3];
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            vehicleTempData.setVehicle_id(entry.getKey());
            vehicleTempData.setAggregate_year_month(targetMonth);
            vehicleTempData.setAggregate_week(targetWeek);
            for (TripCanStrPojo trip : entry.getValue()) {
                if (!trip.getType().equals("")) {
                    type = trip.getType();
                }
                // 5-1、月行驶里程统计
                totalMilage += trip.getOdoTrip();
                if (trip.getOdoTrip() != 0) {
                    LocalDate dateKey = LocalDate.parse(trip.getTargetDate().replace("-",""), dtf);
                    // 5-1 周行驶里程统计
                    odoTripCnt += trip.getOdoTrip();
                    
                    // 5-2、月行驶天数统计 5-3、日驾驶次数统计
                    if (dateAndFrequencyMap.containsKey(dateKey)) {
                        dateAndFrequencyMap.put(dateKey, dateAndFrequencyMap.get(dateKey) + 1);
                    } else {
                        dateAndFrequencyMap.put(dateKey, 1);
                    }
                    // 5-4、日驾驶里程统计
                    if (odoTripMap.containsKey(dateKey)) {
                        odoTripMap.put(dateKey, odoTripMap.get(dateKey) + trip.getOdoTrip());
                    } else {
                        odoTripMap.put(dateKey, trip.getOdoTrip());
                    }
                    // 5-5、日驾驶时间统计
                    if (drivingTimeMap.containsKey(dateKey)) {
                        drivingTimeMap.put(dateKey, drivingTimeMap.get(dateKey)
                                + trip.getDrivingTime());
                    } else {
                        drivingTimeMap.put(dateKey, trip.getDrivingTime());
                    }
                    // 5-15、APP月有效驾驶时间统计
                    totaleffectiveDrivingDuration += trip.getDrivingTime();
                    
                    // 5-17、APP行驶次数统计
                    totalDrivingTimes++;
                    // 5-18、耗油统计				
                    fuelEfficiency += trip.getFuelEfficiency();
                }
                // 5-8-5 不管有没有效，都统计进来
                totalDrivingDuration += trip.getDrivingTime();
//                totalDrivingTimes += trip.getDrivingTime();
                // 5-2-week 周耗油量统计
                fuelCnt += trip.getFuelEfficiency();
                // 5-3 找出本周最大油门开度
                if (maxOpenDegree < trip.getMaxThrottleOpenDegree()) {
                    maxOpenDegree = trip.getMaxThrottleOpenDegree();
                }
                // 5-4-week 周油门开度统计
                openDegreeCnt += trip.getTotalThrottleOpenDegree();
                // 5-5-week 周油门开度数据量统计
                recordsCnt += trip.getThrottleDataRecords();
                // 5-6、月驾驶时间统计
                drivingTimeCnt += trip.getDrivingTime();
                // 5-7、雨刷器使用时间统计
                wiperTimeCnt += trip.getWiperUseTime();
                // 5-8、空调使用时间统计
                airTimeCnt += trip.getAirConditioningUseTime();
                // 5-9、内外循环时间统计
//                innerCnt += Math.ceil((float)trip.getInnerCirculationTime() / 60);
//                outerCnt += Math.ceil((float)trip.getOuterCirculationTime() / 60);
                innerCnt += trip.getInnerCirculationTime();
                outerCnt += trip.getOuterCirculationTime();
                // 判断  5-9的时间段
                // ig_off所在日期在（处理对象月+1）月的数据不进行统计 ,即ig_off日期在本月最后一天之后的数据不统计
                if (!HcrUtil.dateStrToLocalDate(trip.getIgOff(),"yyyy-MM-dd HH:mm:ss.SSS").isAfter(endLocalDate)) {
                    int[] tmpRs = HcrUtil.distributeTime(
                    		HcrUtil.dateStrToLocalDateTime(trip.getIgOn(),"yyyy-MM-dd HH:mm:ss.SSS"),
                    		HcrUtil.dateStrToLocalDateTime(trip.getIgOff(),"yyyy-MM-dd HH:mm:ss.SSS"));
                    for (int i = 0; i < drivingTimes.length; i++) {
                        drivingTimes[i] += tmpRs[(i + 1) % 3];
                    }
                }else {
                	LocalDate flg = HcrUtil.dateStrToLocalDate(trip.getIgOff(),"yyyy-MM-dd HH:mm:ss.SSS");
                	System.out.println("skip:::::"+endLocalDate+","+flg);
                }
                
                // 5-10、驾驶模式时间统计
                modeTime += trip.getSportModeTime() + trip.getPowerModeTime();
                // 5-11、驾驶时间段统计
                /*************************************/
                // 5-12、急刹车次数统计
                suddenBrakeCnt += trip.getSuddenBrakeTimes();
                // 5-13、至今行驶行程统计
                if (trip.getOdoLatest() > maxOdoLatest) {
                    maxOdoLatest = trip.getOdoLatest();
                }
                
                // 5-14、APP最高车速统计
                if (trip.getMaxSpeed() != null && trip.getMaxSpeed() > maxSpeed) {
                	maxSpeed = trip.getMaxSpeed();
                }
                
                // 5-18、耗油统计				
                fuelEfficiency += trip.getFuelEfficiency();
            }
            
            // 获取月最大天数
            int day = DateRange.size();
            // 5-3-1、按trip_can.target_date分组，累计相同日期的记录条数。
            // 5-3-2、统计驾驶次数在一个范围内的天数。
            int[] drivingFrequency = HcrUtil.distributeValue(dateAndFrequencyMap.values(), drivingFrequencyArr);
            drivingFrequency[0] = day - dateAndFrequencyMap.size();
            // 5-4-1、按trip_can.target_date分组，累计trip_can.odo_trip字段。
            // 5-4-2、统计里程在一个范围内的天数。
            int[] odoTrip = HcrUtil.distributeValue(odoTripMap.values(), odoTripArr);
            odoTrip[0] = day - odoTripMap.size();
            // 5-5-1、按trip_can.target_date分组，累计trip_can.driving_time字段。
            // 5-5-2、统计驾驶时间在一个范围内的天数。
            int[] drivingTimeDestribution = HcrUtil.distributeValue(drivingTimeMap.values(), drivingTimeArr);
            drivingTimeDestribution[0] = day - drivingTimeMap.size();
            // 5-9-3、计算内外循环总时间,百分比在下面计算
            int circulationCnt = innerCnt + outerCnt;
            
            // 5-6、计算油耗(结果：单位“升/百公里”，四舍五入保留1位小数。)
            double avgFuel = 0;
            if (odoTripCnt != 0) {
                
                avgFuel = BigDecimal.valueOf(fuelCnt)
                        .divide((BigDecimal.valueOf(10 * odoTripCnt)), 1, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
            }
            /**
             * 计算雨刷与空调的数据
             */
            double wiper = 0;
            double air = 0;
            if (drivingTimeCnt != 0) {
                // 5-7-1、雨刷器使用比例计算
                wiper = BigDecimal.valueOf(wiperTimeCnt).divide(BigDecimal.valueOf(drivingTimeCnt), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100))
                        .doubleValue();
                // 5-8-1、空调使用比例计算
                air = BigDecimal.valueOf(airTimeCnt).divide(BigDecimal.valueOf(drivingTimeCnt), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100))
                        .doubleValue();
            }
         	/**
         	 *  空调内外循环
         	 */
            int innerDistribution = 0;
            int outerDistribution = 0;
            if (circulationCnt != 0) {
                innerDistribution = Math.round((float)innerCnt * 100 / circulationCnt);
                outerDistribution = 100 - innerDistribution;
            }
            
            
            vehicleTempData.setVehcile_type(type);
            // Step-【5-1】周-行驶里程统计
            vehicleTempData.setTotal_milage(totalMilage);
            // Step-【5-3】找出本周最大油门开度
            vehicleTempData.setMax_throttle_open_degree(maxOpenDegree);
            // Step-【5-6】周-油耗（ml）
            vehicleTempData.setFuel_efficiency(fuelCnt);
            // Step-【5-7】 计算平均油门开度
            float avgOpenDegree = 0;
            if (recordsCnt != 0) {
                avgOpenDegree = BigDecimal.valueOf(openDegreeCnt)
                        .divide((BigDecimal.valueOf(recordsCnt)), 2, BigDecimal.ROUND_HALF_UP)
                        .floatValue();
            }
            vehicleTempData.setAvg_throttle_open_degree(avgOpenDegree);

            // Step-【5-8-12】:统计行驶里程
            vehicleTempData.setOdo_trip(odoTripCnt);
            // Step-【5-8-1】:统计驾驶天数
            vehicleTempData.setDriving_days(dateAndFrequencyMap.size());
            // Step-【5-8-2】:周有效行驶次数分布-次数分布统计
            //{title:{ "0次", "~2次", "2~5次", "5~10次", "10~20次", "21次~" },value:[0,10,16,1,1,0]	}																						
            vehicleTempData.setDaily_driving_times(HcrUtil.ArrayToList(drivingFrequency));
            // Step-【5-8-3】:周-行驶里程分布
            //{title:{ "0H", "~0.5H", "~1H", "~2H", "~3H", "3H~" },value:[0,10,16,1,1,0]	}	
            vehicleTempData.setDaily_driving_milage(HcrUtil.ArrayToList(odoTrip));
            // Step-【5-8-4】:周-有效行驶时间分布
            //{title:{ "0km", "~10km", "~30km", "~100km", "~200km", "201km~" },value:[0,10,16,1,1,0]	}	
            vehicleTempData.setDaily_driving_duration(HcrUtil.ArrayToList(drivingTimeDestribution));
            // Step-【5-8-5】:周-行驶时间（秒）
            vehicleTempData.setDriving_time(totalDrivingDuration);
            // Step-【5-8-14｝:周-有效行驶时间（秒）
            vehicleTempData.setEffective_driving_time(totaleffectiveDrivingDuration);
            // Step-【5-8-6】:周-雨刷器使用时间（秒）
            vehicleTempData.setWiper_use_time(BigDecimal.valueOf(wiperTimeCnt));
            // Step-【5-8-7】:周-空调使用时间（秒）																							
            vehicleTempData.setAir_conditioning_use_time(BigDecimal.valueOf(airTimeCnt));
            // Step 【5-8-8】:周-内循环时间（秒）- 外循环																							
            vehicleTempData.setOuter_circulation_time(outerCnt);
            // Step 【5-8-8】:周-内循环时间（秒）- 内循环
            vehicleTempData.setInner_circulation_time(innerCnt);
            // Step 【5-8-9】:周-运动模式时间（秒） 注：其他模式时间=总时间-运动模式时间
            vehicleTempData.setSport_mode_time(modeTime);
            // Step:【5-8-10】:周-行驶时间段
            vehicleTempData.setDriving_period_duration(HcrUtil.ArrayToList(drivingTimes));
            // Step:【5-8-10】:周急刹车次数
            vehicleTempData.setSudden_brake_times(suddenBrakeCnt);
            // Step:【5-8-13】:周最高车速（km/h）
            vehicleTempData.setMax_speed(maxSpeed);
            vehicleTempData.setOdo_latest(maxOdoLatest);
            vehicleTempData.setTotal_driving_times(totalDrivingTimes);
            weekTemp.add(vehicleTempData);
        }
	
    }
	
	/**
     * 使用周次temp文件，计算月次数据
     * @param monthTemp    周次temp文件队列
     * @param targetMonth
     * @param milageDayPerMonthList
     * @param drivingDayPerFrequencyList
     * @param drivingDayPerMilageList
     * @param drvDayPerDrivingTimeList
     * @param wiperUseTimeList
     * @param airConditioningList
     * @param circulationDestributionList
     * @param powerSportUseTimeList
     * @param drivingTimezoneList
     * @param suddenBrakeTimesList
     * @param rmtSrvMilagePerMonthList
     * @param customerAppMonthInfList
     * @throws Exception
     */
    public static void processByTempData(List<MonthTempData> monthTemp,
                                          Integer targetMonth
            , List<MilageDayPerMonthInf> milageDayPerMonthList
            , List<DrivingDayPerFrequencyInf> drivingDayPerFrequencyList
            , List<DrivingDayPerMilageInf> drivingDayPerMilageList
            , List<DrvDayPerDrivingTimeInf> drvDayPerDrivingTimeList
            , List<WiperUseTimeInf> wiperUseTimeList
            , List<AirConditioningUseTimeInf> airConditioningList
            , List<CirculationDestributionInf> circulationDestributionList
            , List<PowerSportUseTimeInf> powerSportUseTimeList
            , List<DrivingTimezoneInf> drivingTimezoneList
            , List<SuddenBrakeTimesInf> suddenBrakeTimesList
            , List<RmtSrvMilagePerMonthInf> rmtSrvMilagePerMonthList
            , List<CustomerAppMonthInf> customerAppMonthInfList) throws Exception {
        MilageDayPerMonthInfBuilder milageDayPerMonth = MilageDayPerMonthInf.builder();
        DrivingDayPerFrequencyInfBuilder drivingDayPerFrequency = DrivingDayPerFrequencyInf.builder();
        DrivingDayPerMilageInfBuilder drivingDayPerMilage = DrivingDayPerMilageInf.builder();
        DrvDayPerDrivingTimeInfBuilder drivingTime = DrvDayPerDrivingTimeInf.builder();
        WiperUseTimeInfBuilder wiperUseTime = WiperUseTimeInf.builder();
        AirConditioningUseTimeInfBuilder airConditioning = AirConditioningUseTimeInf.builder();
        CirculationDestributionInfBuilder circulationDestribution = CirculationDestributionInf.builder();
        PowerSportUseTimeInfBuilder powerSportUseTime = PowerSportUseTimeInf.builder();
        DrivingTimezoneInfBuilder drivingTimezone = DrivingTimezoneInf.builder();
        SuddenBrakeTimesInfBuilder suddenBrakeTimes = SuddenBrakeTimesInf.builder();
        RmtSrvMilagePerMonthInfBuilder rmtSrvMilagePerMonth = RmtSrvMilagePerMonthInf.builder();
        CustomerAppMonthInfBuilder customerAppMonth = CustomerAppMonthInf.builder();
        Map<String, List<MonthTempData>> tempMap = monthTemp.stream().collect(Collectors.groupingBy(MonthTempData :: getVehicle_id));
        for(Entry<String, List<MonthTempData>> entry: tempMap.entrySet()) {
            String vin = entry.getKey();
            int totalMilage = 0;
            int totalDriveDay = 0;
            int drivingTimeCnt = 0;
            int wiperTimeCnt = 0;
            int airTimeCnt = 0;
            int innerCnt = 0;
            int outerCnt = 0;
            int modeTime = 0;
            int suddenBrakeCnt = 0;
            int maxOdoLatest = 0;
            int maxSpeed = 0;
            int totalDrivingDuration = 0;
            int totalDrivingTimes = 0;
            int fuelCost = 0;
            int Max_throttle_sum = 0;
            Double fuelEfficiency = 0.0;
            String type = "";
            Map<String, Integer> dateAndFrequencyMap = new HashMap<>();
            Map<String, Integer> odoTripMap = new HashMap<>();
            Map<String, Integer> drivingTimeMap = new HashMap<>();
            MonthTempData monthData = new MonthTempData();
            /**
             * "0次", "~2次", "~5次", "~10次", "~20次", "21次~"
             * 次数别，分布统计，x周逐行累加
             */
            List<Integer> drivingFrequency = new ArrayList<Integer>(6);
            List<Integer> odoTripMonth = new ArrayList<Integer>();
            List<Integer> drivingTimeDestribution = new ArrayList<Integer>();
            List<Integer> Driving_period_duration = new ArrayList<Integer>();
            /**
             * 驾驶天数
             */
            for (MonthTempData weekTemp : entry.getValue()) {
                // 5-1、月行驶里程统计
                totalMilage += weekTemp.getOdo_trip();
                // 5-2、月行驶天数统计
                totalDriveDay += weekTemp.getDriving_days();
                HcrUtil.listCombine(drivingFrequency, weekTemp.getDaily_driving_times());
                HcrUtil.listCombine(odoTripMonth, weekTemp.getDaily_driving_milage());
                HcrUtil.listCombine(drivingTimeDestribution, weekTemp.getDaily_driving_duration());
                HcrUtil.listCombine(Driving_period_duration, weekTemp.getDriving_period_duration());
                if (weekTemp.getOdo_trip() != 0) {
                    String dateKey = weekTemp.getAggregate_year_month()+weekTemp.getAggregate_week()+"";
                    // 5-4、日驾驶里程统计
                    if (odoTripMap.containsKey(dateKey)) {
                        odoTripMap.put(dateKey, odoTripMap.get(dateKey) + weekTemp.getOdo_trip());
                    } else {
                        odoTripMap.put(dateKey, weekTemp.getOdo_trip());
                    }
                    // 5-5、日驾驶时间统计
                    if (drivingTimeMap.containsKey(dateKey)) {
                        drivingTimeMap.put(dateKey, drivingTimeMap.get(dateKey)
                                + weekTemp.getDriving_time());
                    } else {
                        drivingTimeMap.put(dateKey, weekTemp.getDriving_time());
                    }
                    // 5-15、APP月有效驾驶时间统计
                    totalDrivingDuration += weekTemp.getEffective_driving_time();
                    // 5-17、APP行驶次数统计
                    totalDrivingTimes += weekTemp.getTotal_driving_times();

                }
                // 5-6、月驾驶时间统计
                drivingTimeCnt += weekTemp.getDriving_time();
                // 5-7、雨刷器使用时间统计
                wiperTimeCnt += weekTemp.getWiper_use_time().intValue();
                // 5-8、空调使用时间统计
                airTimeCnt += weekTemp.getAir_conditioning_use_time().intValue();
                // 5-9、内外循环时间统计
                innerCnt += weekTemp.getInner_circulation_time();
                outerCnt += weekTemp.getOuter_circulation_time();
                // 5-10、驾驶模式时间统计
                modeTime += weekTemp.getSport_mode_time();
                // 5-11、驾驶时间段统计
                // 判断  5-9的时间段
                // ig_off所在日期在（处理对象月+1）月的数据不进行统计 ,即ig_off日期在本月最后一天之后的数据不统计
//               if (!trip.getIgOff().toLocalDate().isAfter(endLocalDate)) {
//                   int[] tmpRs = HcrUtil.distributeTime(
//                           weekTemp.getIgOn().toLocalDateTime(),
//                           weekTemp.getIgOff().toLocalDateTime());
//                   for (int i = 0; i < drivingTimes.length; i++) {
//                       drivingTimes[i] += tmpRs[(i + 1) % 3];
//                   }
//               }

                // 5-12、急刹车次数统计
                suddenBrakeCnt += weekTemp.getSudden_brake_times();
                // 5-13、至今行驶行程统计
                if (weekTemp.getOdo_latest() > maxOdoLatest) {
                    maxOdoLatest = weekTemp.getOdo_latest();
                }
                // 5-14、APP最高车速统计
                if (weekTemp.getMax_speed() > maxSpeed) {
                    maxSpeed = weekTemp.getMax_speed();
                }
                // 5-18、耗油统计
                fuelEfficiency += weekTemp.getFuel_efficiency();
            }

            // 1 dateAndFrequencyMap.size()是驾驶天数
            milageDayPerMonthList.add(milageDayPerMonth.vehicleId(vin).aggregateYearMonth(targetMonth).totalMilage(totalMilage)
                    .totalDrivingDays(totalDriveDay)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 2 按驾驶次数统计天数
            String drivingFrequencyStr = StringUtils.join(drivingFrequency,",");
            drivingDayPerFrequencyList.add(drivingDayPerFrequency.vehicleId(vin)
                    .aggregateYearMonth(targetMonth).drivingFrequencyRange(frequency)
                    .totalDays(drivingFrequencyStr)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 3 按行驶里程统计天数
            String milTotslDays = StringUtils.join(odoTripMonth,",");
            drivingDayPerMilageList.add(drivingDayPerMilage.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .milageRange(milage).totalDays(milTotslDays)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 4 驾驶时间=====improcess
            String drivingTimes = StringUtils.join(drivingTimeDestribution,",");
            drvDayPerDrivingTimeList.add(drivingTime.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .drivingTimeRange(timeRange).totalDays(drivingTimes)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            double wiper = 0;
            double air = 0;
            if (drivingTimeCnt != 0) {
                // 5-7-1、雨刷器使用比例计算
                wiper = BigDecimal.valueOf(wiperTimeCnt).divide(BigDecimal.valueOf(drivingTimeCnt), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100))
                        .doubleValue();
                // 5-8-1、空调使用比例计算
                air = BigDecimal.valueOf(airTimeCnt).divide(BigDecimal.valueOf(drivingTimeCnt), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100))
                        .doubleValue();
            }
            // 5-7-2 雨刷
            wiperUseTimeList.add(wiperUseTime.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .wiperUseTime(wiperTimeCnt).wiperUseTimeDestribution(BigDecimal.valueOf(wiper))
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-8-2 空调
            airConditioningList.add(airConditioning.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .airConditioningUseTime(airTimeCnt).airConditioningUseTimeDestribution(BigDecimal.valueOf(air))
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-9-2 内外循环
            int innerDistribution = 0;
            int outerDistribution = 0;
            // 5-9-3、计算内外循环总时间,百分比在下面计算
            int innernum = (int) Math.ceil((float)innerCnt/ 60);
            int outernum = (int) Math.ceil((float)outerCnt/ 60);
            int circulationCnt = innernum + outernum;
            if (circulationCnt != 0) {
                innerDistribution = Math.round((float)innernum * 100 / circulationCnt);
                outerDistribution = 100 - innerDistribution;
            }
            circulationDestributionList.add(circulationDestribution.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .innerCirculationTime(innernum).outerCirculationTime(outernum)
                    .innerCirculationDistribution(innerDistribution)
                    .outerCirculationDistribution(outerDistribution)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-10 驾驶模式时间
            powerSportUseTimeList.add(powerSportUseTime.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .powerSportModeTime(modeTime).otherModeTime(drivingTimeCnt - modeTime)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-11 驾驶时间段
            // 5-11-3、分别合计处理对象月和（处理对象月-1）月的时间段拆分结果
            String zoneDrivingTime = StringUtils.join(Driving_period_duration,",");
            drivingTimezoneList.add(drivingTimezone.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .timeZone(timeZoneStart)
                    .timeZoneStart("")
                    .timeZoneEnd("")
                    .drivingTime(zoneDrivingTime)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-12 急刹车
            suddenBrakeTimesList.add(suddenBrakeTimes.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .suddenBrakeTimes(suddenBrakeCnt)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-13 至今行驶里程
            rmtSrvMilagePerMonthList.add(rmtSrvMilagePerMonth.vehicleId(vin).aggregateYearMonth(targetMonth)
                    .totalMilage(totalMilage).latestOdoInf(maxOdoLatest)
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
            // 5-16、APP平均车速统计
            int avgSpeed = 0;
            if (totalDrivingDuration != 0) {
                avgSpeed = Math.round((float)totalMilage * 3600 / totalDrivingDuration);
            }
            // 12 APP数据统计
            customerAppMonthInfList.add(customerAppMonth.vehicleId(vin).aggregateYearMonth(targetMonth)
                    // 5-14
                    .maxSpeed(maxSpeed).totalDrivingDuration(drivingTimeCnt)
                    // 5-16
                    .avgSpeed(avgSpeed).totalDrivingTimes(totalDrivingTimes)
                    // 5-18
                    .fuelEfficiency(Long.valueOf(Math.round(fuelEfficiency/1000)).intValue())
                    .registDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate())
                    .updateDate(ZonedDateTime.now(ZoneId.of("UTC+8")).toLocalDate()).build());
        }
    }
    private static final String frequency = "0次,~2次,~5次,~10次,~20次,21次~";
    private static final String timeRange = "0H,~0.5H,~1H,~2H,~3H,3H~";
    private static final String milage ="0km,~10km,~30km,~100km,~200km,201km~";
    private static final String timeZoneStart = "5:00-8:59,9:00-17:59,18:00-4:59";
}
