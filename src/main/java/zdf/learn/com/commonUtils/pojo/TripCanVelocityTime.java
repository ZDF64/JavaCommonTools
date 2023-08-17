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
@MysqlSchema(name = "trip_can_velocity_time")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCanVelocityTime implements Serializable{
    
    @Id
    private long id;
    
    @Column(name = "vehicle_id")
    private String vehicleId;
    
    private ZonedDateTime igOn;

    @Column(name = "target_date")
    private LocalDate targetDate;
    
//    @Column(name = "trip_cnt")
//    private Integer tripCnt;
    
    @Column(name = "velocity_upper_limit")
    private Integer velocityUpperLimit;
    
    @Column(name = "velocity_lower_limit")
    private Integer velocityLowerLimit;
    
    @Column(name = "velocity_time")
    private String velocityTime;
    
    private ZonedDateTime createtime;
}
