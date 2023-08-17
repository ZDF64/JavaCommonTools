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
@MysqlSchema(name = "weekly_fuel_efficency_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyFuelEfficencyInf implements Serializable {
    
    @Id
    private long weeklyFuelEfficencyInfId;
    
    private String vehicleId;
    
    private String vehicleType;
    
    private Integer aggregateYearMonth;
    
    private Integer aggregateWeek;
    
    private Double fuelEfficiency;
    
    private int totalMilage;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
