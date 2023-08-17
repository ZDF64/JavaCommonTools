package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "vehicle_message_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleMessageInf implements Serializable {
    
    @Id
    private long vehicleMessageInfId;
    
    private String vehicleId;
    
    private String messageId;
    
    private Integer milage;
    
    private Integer startMonth;
    
    private Integer endMonth;
    
    private Integer destribution;
    
    private Integer lastMonth;
    
    private Integer monthBeforeLastMonth;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
