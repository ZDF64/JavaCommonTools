package zdf.learn.com.commonUtils.pojo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCanRemoteOdoTimePojo {

    private String vehicleId;

    private Date targetDate;

    private Integer drivingTime;

    private Integer odoTrip;

    private String settingValue;
}
