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
@MysqlSchema(name = "trip_milage_driving_time_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripMilageDrivingTimeInf implements Serializable {
    
    @Id
    private long tripMilageDrivingTimeInfId;
    
    private String vehicleId;
    
    private Integer tripYearMonth;
    
    private String tripDateJson;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
}
