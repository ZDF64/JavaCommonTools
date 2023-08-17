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
@MysqlSchema(name = "rmt_srv_milage_per_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RmtSrvMilagePerMonthInf implements Serializable {
    
    @Id
    private long rmtSrvMilagePerMonthInfId;
    
    private String vehicleId;
    
    private Integer aggregateYearMonth;
    
    private Integer totalMilage;
    
    private Integer latestOdoInf;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
