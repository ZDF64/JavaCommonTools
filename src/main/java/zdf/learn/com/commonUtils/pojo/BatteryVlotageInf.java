package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;

@SuppressWarnings("serial")
@MysqlSchema(name = "battery_vlotage_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatteryVlotageInf implements Serializable {
    
    @Id
    private long batteryVlotageInfId;
    
    private String vehicleId;
    
    private String lowestVlotage;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
