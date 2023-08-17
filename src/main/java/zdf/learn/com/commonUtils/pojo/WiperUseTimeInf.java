package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "wiper_use_time_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WiperUseTimeInf implements Serializable {
    
    @Id
    private long wiperUseTimeInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer wiperUseTime;
    
    private BigDecimal wiperUseTimeDestribution;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
