package zdf.learn.com.commonUtils.pojo;

import zdf.learn.com.commonUtils.annotation.MysqlSchema.Id;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCanVelocityTimePojo implements Serializable{
	@Id
    private long id;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "velocity_time")
    private String velocityTime;

}
