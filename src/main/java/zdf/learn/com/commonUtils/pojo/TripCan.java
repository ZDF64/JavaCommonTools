package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "trip_can")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCan implements Serializable{
    
    @Id
    private long id;
    
    @Column(name = "vehicle_id")
    private String vehicleId;
    
    @Column(name = "target_date")
    private LocalDate targetDate;
    
//    @Column(name = "trip_cnt")
//    private Integer tripCnt;
    
    @Column(name = "ig_on")
    private ZonedDateTime igOn;
    
    @Column(name = "ig_off")
    private ZonedDateTime igOff;
    
    @Column(name = "driving_time")
    private Integer drivingTime;
    
    @Column(name = "fuel_efficiency")
    private Double fuelEfficiency;
    
    @Column(name = "max_throttle_open_degree")
    private float maxThrottleOpenDegree;
    
    @Column(name = "total_throttle_open_degree")
    private Double totalThrottleOpenDegree;
    
    @Column(name = "throttle_data_records")
    private Integer throttleDataRecords;
    
    @Column(name = "sudden_brake_times")
    private Integer suddenBrakeTimes;
    
    @Column(name = "eco_mode_time")
    private Integer ecoModeTime;
    
    @Column(name = "normal_mode_time")
    private Integer normalModeTime;
    
    @Column(name = "sport_mode_time")
    private Integer sportModeTime;
    
    @Column(name = "power_mode_time")
    private Integer powerModeTime;
    
    @Column(name = "snow_mode_time")
    private Integer snowModeTime;
    
    @Column(name = "inner_circulation_time")
    private Integer innerCirculationTime;
    
    @Column(name = "outer_circulation_time")
    private Integer outerCirculationTime;
    
    @Column(name = "wiper_use_time")
    private Integer wiperUseTime;
    
    @Column(name = "odo_trip")
    private Integer odoTrip;
    
    @Column(name = "odo_latest")
    private Integer odoLatest;
    
    private String type;
    
    @Column(name = "air_conditioning_use_time")
    private Integer airConditioningUseTime;
    
    private Integer maxSpeed;
    private String assistDataTime;

    private ZonedDateTime createtime;
}
