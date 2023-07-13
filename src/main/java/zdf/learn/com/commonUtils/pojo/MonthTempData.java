package zdf.learn.com.commonUtils.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("serial")
public class MonthTempData implements Serializable{
	private String vehicle_id;
	private int aggregate_year_month;
	private int aggregate_week;
	//车型
	private String vehcile_type;
	//周-行驶里程（km）
	private int odo_trip;
	//周-行驶总里程（km）
	private int odo_latest;
	//周-油耗（ml）
	private Double fuel_efficiency;
	//周-行驶里程统计结果
	private int total_milage;
	//周-平均油耗
	private Double avgFuleWeek;
	//周-有效行驶日数总和
	private int driving_days;
	//周-有效行驶次数分布{2,10,16,1,1,0}
	private List<Integer> daily_driving_times;
	//周-行驶里程分布{2,17,11,0,0,0}
	private List<Integer> daily_driving_milage;
	//周-有效行驶时间分布{2,10,12,5,1,0}
	private List<Integer> daily_driving_duration;
	//周-行驶时间（秒）
	private int driving_time;
	//周-有效行驶时间（秒）
	private int effective_driving_time;
	//周-雨刷器使用时间（秒）
	private BigDecimal wiper_use_time;
	//周-空调使用时间（秒）
	private BigDecimal air_conditioning_use_time;
	//周-内循环时间（秒）
	private int inner_circulation_time;
	//周-外循环时间（秒）
	private int outer_circulation_time;
	//周-运动模式时间（秒）
	private int sport_mode_time;
	//周-行驶时间段
	private List<Integer> driving_period_duration;
	//周-急刹车次数
	private int sudden_brake_times;
	//周-最高车速（km/h）
	private int max_speed;
	//找出本周最大油门开度
	private float max_throttle_open_degree;
	//计算平均油门开度
	private float avg_throttle_open_degree;
	//计算周有效行驶次数
	private int total_driving_times;

}
