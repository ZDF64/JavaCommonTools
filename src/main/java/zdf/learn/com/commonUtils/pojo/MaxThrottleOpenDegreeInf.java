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
@MysqlSchema(name = "max_throttle_open_degree_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaxThrottleOpenDegreeInf implements Serializable {
    
    @Id
    private long maxThrottleOpenDegreeInfId;
    
    private String vehicleIdentifyId;
    
    private Integer aggregateYearMonth;
    
    private Integer aggregateYearWeek;
    
    private float maxThrottleOpenDegree;
    
    private float avgThrottleOpenDegree;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
