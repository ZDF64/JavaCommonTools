package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.ZonedDateTime;
import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Column;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "assist_remote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssistRemote implements Serializable{
    
    @Id
    private Long id;
    
    @Column(name = "vehicle_id")
    private String vehicleId;
    
    @Column(name = "trip_cnt")
    private Integer tripCnt;
    
    @Column(name = "ig_on")
    private ZonedDateTime igOn;
    
    @Column(name = "ig_off")
    private ZonedDateTime igOff;
    
}
