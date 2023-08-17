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
@MysqlSchema(name = "power_sport_use_time_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PowerSportUseTimeInf implements Serializable {
    
    @Id
    private long powerSportUseDayInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer powerSportModeTime;
    
    private Integer otherModeTime;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
}
