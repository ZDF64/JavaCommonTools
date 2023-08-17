package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "trip_remote")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripRemote implements Serializable {
    
    @Id
    private long id;
    
    private String vehicleId;
    
    private String gbookReceiveSeq;
    
    private ZonedDateTime gbookReceiveTime;
    
    private ZonedDateTime monitoringTime;
    
    private String settingId;
    
    private String ecuId;
    
    private String pidId;
    
    private String settingValue;
    
    private ZonedDateTime createtime;
}
