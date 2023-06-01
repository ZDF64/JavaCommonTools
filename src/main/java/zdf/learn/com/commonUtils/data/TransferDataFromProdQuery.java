package zdf.learn.com.commonUtils.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import zdf.learn.com.commonUtils.MultiTask.inf.BatchComputer;
import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanFetchDbToCSV;

@Service
public class TransferDataFromProdQuery {
	
	public void fetchToLocal() {
		String sql_rmt = "SELECT rmt_srv_milage_per_month_inf_id, vehicle_id,aggregate_year_month,total_milage,latest_odo_inf,regist_date,update_date FROM rmt_srv_milage_per_month_inf where aggregate_year_month = 202304";
		String sql_air = "SELECT air_conditioning_use_time_inf_id, vehicle_id, aggregate_year_month, air_conditioning_use_time, air_conditioning_use_time_destribution, regist_date, update_date FROM air_conditioning_use_time_inf where aggregate_year_month = 202304";
		String sql_avg_vel = "SELECT avg_velocity_destribution_inf_id, vehicle_id, aggregate_year_month, data_ratio, regist_date, update_date, data_velocity_time FROM avg_velocity_destribution_inf where aggregate_year_month = 202304";
		String sql_cir_des = "SELECT circulation_destribution_inf_id, vehicle_id, aggregate_year_month, inner_circulation_time, outer_circulation_time, regist_date, update_date, inner_circulation_distribution, outer_circulation_distribution FROM circulation_destribution_inf where aggregate_year_month = 202304";
		String sql_cust_app = "SELECT customer_app_month_inf_id, vehicle_id, aggregate_year_month, max_speed, total_driving_duration, avg_speed, total_driving_times, fuel_efficiency, regist_date, update_date FROM customer_app_month_inf where aggregate_year_month = 202304";
		String sql_drv_fre = "SELECT driving_day_per_frequency_inf_id, vehicle_id, aggregate_year_month, driving_frequency_range, total_days, regist_date, update_date FROM driving_day_per_frequency_inf where aggregate_year_month = 202304";
		String sql_drv_day = "SELECT driving_day_per_milage_inf_id, vehicle_id, aggregate_year_month, milage_range, total_days, regist_date, update_date FROM driving_day_per_milage_inf where aggregate_year_month = 202304";
		String sql_max_thr = "SELECT max_throttle_open_degree_inf_id, vehicle_identify_id, aggregate_year_month, aggregate_year_week, max_throttle_open_degree, avg_throttle_open_degree, regist_date, update_date FROM max_throttle_open_degree_inf where aggregate_year_month = 202304";
		String sql_mil_day = "SELECT milage_day_per_month_inf_id, vehicle_id, aggregate_year_month, total_milage, total_driving_days, regist_date, update_date FROM milage_day_per_month_inf where aggregate_year_month = 202304";
		String sql_power = "SELECT power_sport_use_day_inf_id, vehicle_id, aggregate_year_month, power_sport_mode_time, other_mode_time, regist_date, update_date FROM power_sport_use_time_inf where aggregate_year_month = 202304";
		String sql_sudden = "SELECT sudden_brake_times_inf_id, vehicle_id, aggregate_year_month, sudden_brake_times, regist_date, update_date FROM sudden_brake_times_inf where aggregate_year_month = 202304";
		String sql_trip_mil = "SELECT trip_milage_driving_time_inf_id, vehicle_id, trip_year_month, trip_date_json, regist_date, update_date FROM trip_milage_driving_time_inf where trip_year_month = 202304";
		String sql_v_type ="SELECT v_type_avg_fuel_efficiency_inf_id, vehicle_type, aggregate_year_month, avg_fuel_efficiency, regist_date, update_date FROM v_type_avg_fuel_efficiency_inf where aggregate_year_month = 202304";
		String sql_vel_des = "SELECT velocity_destribution_month_inf_id, vehicle_id, aggregate_year_month, low_part1_speed_percent, low_part2_speed_percent, low_speed_percent, intermediate_speed_percent, high_speed_percent, regist_date, update_date FROM velocity_destribution_month_inf where aggregate_year_month = 202304";
		String sql_weekly ="SELECT weekly_fuel_efficency_inf_id, vehicle_id, aggregate_year_month, vehicle_type, aggregate_week, fuel_efficiency, regist_date, update_date, total_milage FROM weekly_fuel_efficency_inf where aggregate_year_month = 202304";
		String sql_wiper ="SELECT wiper_use_time_inf_id, vehicle_id, aggregate_year_month, wiper_use_time, wiper_use_time_destribution, regist_date, update_date FROM wiper_use_time_inf where aggregate_year_month = 202304";
		
		BatchComputer batchCom = new BatchComputer(12,6);
		
		String[] sqls = new String[] {sql_air,sql_avg_vel,sql_cir_des,sql_cust_app,sql_drv_fre,sql_drv_day,sql_max_thr,sql_mil_day,sql_power,sql_sudden,sql_trip_mil,sql_v_type,sql_vel_des,sql_weekly,sql_wiper};
		int maxBlock = 10000;
		for(int i = 0 ; i < 25 ; i ++) {
			for(String sql : sqls) {
				sql = sql.toLowerCase();
				String name = sql.substring(sql.indexOf("from")+4,sql.indexOf("where"));
				System.out.println(name);
				TaskBeanFetchDbToCSV fetchEle = new TaskBeanFetchDbToCSV(sql,"/home/apuser/data/"+name+".csv",i*maxBlock,maxBlock);
				batchCom.addTask(fetchEle);
			}
		}
		
		batchCom.startCompute();
	}
}
