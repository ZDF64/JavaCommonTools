package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "trip_can_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCanStatus implements Serializable {
    
    @Id
    private Long id;
    
    private String vehicleId;
    
    private Integer lastTripCnt;
    
    private LocalDateTime lastTripTime;
    
}
