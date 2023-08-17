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
@MysqlSchema(name = "threshold_value_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThresholdValueInf implements Serializable {
    
    @Id
    private String messageId;
    
    private BigDecimal thresholdValue1;
    
    private BigDecimal thresholdValue2;
    
    private BigDecimal thresholdValue3;
    
    private BigDecimal thresholdValue4;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
