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
@MysqlSchema(name = "circulation_destribution_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CirculationDestributionInf implements Serializable {
    
    @Id
    private long circulationDestributionInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer innerCirculationTime;
    
    private Integer outerCirculationTime;
    
    private Integer innerCirculationDistribution;
    
    private Integer outerCirculationDistribution;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
