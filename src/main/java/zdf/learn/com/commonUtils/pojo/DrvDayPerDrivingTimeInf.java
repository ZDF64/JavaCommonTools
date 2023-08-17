package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "drv_day_per_driving_time_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrvDayPerDrivingTimeInf implements Serializable {
    
    @Id
    private long drvDayPerDrivingTimeInf;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private String drivingTimeRange;
    
    private String totalDays;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
