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
@MysqlSchema(name = "trip_can_throttle_time")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCanThrottleTime implements Serializable{
    
    @Id
    private Long id;
    
    private LocalDate targetDate;
    
    private String vehicleId;
    
    private ZonedDateTime igOn;

//    private Integer tripCnt;
    
    private Integer throttleUpperLimit;
    
    private Integer throttleLowerLimit;
    
    private Integer throttleTime;
    
    private ZonedDateTime createtime;
}
