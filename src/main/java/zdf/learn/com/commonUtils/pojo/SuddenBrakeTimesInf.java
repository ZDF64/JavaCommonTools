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
@MysqlSchema(name = "sudden_brake_times_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuddenBrakeTimesInf implements Serializable {
    
    @Id
    private long suddenBrakeTimesInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer suddenBrakeTimes;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
