package zdf.learn.com.commonUtils.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCanVelocityTimeTempPojo {

    private String vehicleId;

    private Integer week;

    private String dataRatio;

    private String dataVelocityTime;
}
