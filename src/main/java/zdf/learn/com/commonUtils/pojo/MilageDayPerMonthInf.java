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
@MysqlSchema(name = "milage_day_per_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MilageDayPerMonthInf implements Serializable {
    
    @Id
    private long milageDayPerMonthInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer totalMilage;
    
    private Integer totalDrivingDays;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
