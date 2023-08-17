package zdf.learn.com.commonUtils.pojo;

import zdf.learn.com.commonUtils.annotation.MysqlSchema.Id;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripRemoteStrPojo implements Serializable{
	@Id
    private long id;

    private String vehicleId;

    private String gbookReceiveSeq;

    private String gbookReceiveTime;

    private String monitoringTime;

    private String settingId;

    private String ecuId;

    private String pidId;

    private String settingValue;

    private String createtime;
}
