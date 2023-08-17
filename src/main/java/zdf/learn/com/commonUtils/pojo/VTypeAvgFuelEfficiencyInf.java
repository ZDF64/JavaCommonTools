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
@MysqlSchema(name = "v_type_avg_fuel_efficiency_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VTypeAvgFuelEfficiencyInf implements Serializable {
    
    @Id
    private long vTypeAvgFuelEfficiencyInfId;
    
    private String vehicleType;
    
    private Integer aggregateYearMonth;
    
    private Double avgFuelEfficiency;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
