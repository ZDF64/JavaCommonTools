package zdf.learn.com.commonUtils.pojo;


import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@MysqlSchema(name = "trip_remote")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripRemotee implements Serializable {

    @Id
    private long id;

    private String vehicleId;

    private String gbookReceiveSeq;

    // Timestamp
    private String gbookReceiveTime;

    // Timestamp
    private String monitoringTime;

    private String settingId;

    private String ecuId;

    private String pidId;

    private String settingValue;

    // Timestamp
    private String createtime;

}
