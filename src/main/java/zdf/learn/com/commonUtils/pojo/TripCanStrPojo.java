package zdf.learn.com.commonUtils.pojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("serial")
public class TripCanStrPojo implements Serializable{
	
    private long id;

    private String vehicleId;

    private String targetDate;

//    @Column(name = "trip_cnt")
//    private Integer tripCnt;

    private String igOn;

    private String igOff;

    private Integer drivingTime;

    private Double fuelEfficiency;

    private float maxThrottleOpenDegree;

    private Double totalThrottleOpenDegree;

    private Integer throttleDataRecords;

    private Integer suddenBrakeTimes;

    private Integer ecoModeTime;

    private Integer normalModeTime;

    private Integer sportModeTime;

    private Integer powerModeTime;

    private Integer snowModeTime;

    private Integer innerCirculationTime;

    private Integer outerCirculationTime;

    private Integer wiperUseTime;

    private Integer odoTrip;

    private Integer odoLatest;

    private String type;

    private Integer airConditioningUseTime;

    private Integer maxSpeed;
    private String assistDataTime;

    private String createtime;
}
