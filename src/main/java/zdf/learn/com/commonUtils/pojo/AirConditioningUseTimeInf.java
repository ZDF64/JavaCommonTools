package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Id;


@SuppressWarnings("serial")
@MysqlSchema(name = "air_conditioning_use_time_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirConditioningUseTimeInf implements Serializable {
    
    @Id
    private long airConditioningUseTimeInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer airConditioningUseTime;
    
    private BigDecimal airConditioningUseTimeDestribution;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
