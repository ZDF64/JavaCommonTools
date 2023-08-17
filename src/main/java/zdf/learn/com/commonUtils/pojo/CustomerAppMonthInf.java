package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
@SuppressWarnings("serial")
@MysqlSchema(name = "customer_app_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAppMonthInf implements Serializable {
    
    @Id
    private long customerAppMonthInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer maxSpeed;
    
    private Integer totalDrivingDuration;
    
    private Integer avgSpeed;
    
    private Integer totalDrivingTimes;
    
    private Integer fuelEfficiency;

    private LocalDate registDate;
    
    private LocalDate updateDate;
}
