package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.ZonedDateTime;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "last_trip_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LastTripInfo implements Serializable{
    
    @Id
    private long id;
    
    @Column(name = "vehicle_id")
    private String vehicleId;
    
//    @Column(name = "last_trip_cnt")
//    private Integer lastTripCnt;
    
    @Column(name = "last_trip_time")
    private ZonedDateTime lastTripTime;
    
}
