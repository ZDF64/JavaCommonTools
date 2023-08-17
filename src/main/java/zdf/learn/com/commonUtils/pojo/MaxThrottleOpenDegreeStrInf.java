package zdf.learn.com.commonUtils.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("serial")
public class MaxThrottleOpenDegreeStrInf implements Serializable {
	private long maxThrottleOpenDegreeInfId;

    private String vehicleIdentifyId;

    private Integer aggregateYearMonth;

    private Integer aggregateYearWeek;

    private float maxThrottleOpenDegree;

    private float avgThrottleOpenDegree;

    private String registDate;

    private String updateDate;

}
