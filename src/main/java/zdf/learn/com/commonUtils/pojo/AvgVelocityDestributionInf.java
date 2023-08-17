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
@MysqlSchema(name = "avg_velocity_destribution_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvgVelocityDestributionInf implements Serializable {
    
    @Id
    private long avgVelocityDestributionInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private String data_velocity_time;
    
    private String dataRatio;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
