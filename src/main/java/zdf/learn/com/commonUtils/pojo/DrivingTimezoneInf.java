package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;

@SuppressWarnings("serial")
@MysqlSchema(name = "driving_timezone_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingTimezoneInf implements Serializable {
    
    @Id
    private long drivingTimezoneInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private String timeZone;

    private String timeZoneStart;

    private String timeZoneEnd;
    
    private String drivingTime;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
