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
@MysqlSchema(name = "driving_day_per_milage_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingDayPerMilageInf implements Serializable {
    
    @Id
    private long drivingDayPerMilageInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private String milageRange;
    
    private String totalDays;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
