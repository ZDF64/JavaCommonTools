package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
@SuppressWarnings("serial")
@MysqlSchema(name = "decision_result_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecisionResultInf implements Serializable {
    
    @Id
    private long decisionResultInfId;
    
    private String vehicleId;
    
    private long itemTypeId;
    
    private ZonedDateTime decisionExecTime;
    
    private String decisionResult;
    
    private LocalDate registDate;
    
    private LocalDate updateDate;
    
}
