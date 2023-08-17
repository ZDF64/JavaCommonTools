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
@MysqlSchema(name = "velocity_destribution_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VelocityDestributionMonthInf implements Serializable{
    
    @Id
    private long velocityDestributionMonthInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer lowPart1SpeedPercent;
    
    private Integer lowPart2SpeedPercent;
    
    private Integer lowSpeedPercent;
    
    private Integer intermediateSpeedPercent;
    
    private Integer highSpeedPercent;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
}
