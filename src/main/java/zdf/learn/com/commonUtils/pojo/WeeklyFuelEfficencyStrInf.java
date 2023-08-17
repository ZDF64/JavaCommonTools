package zdf.learn.com.commonUtils.pojo;

import zdf.learn.com.commonUtils.annotation.MysqlSchema.Id;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyFuelEfficencyStrInf implements Serializable{
    @Id
    private long weeklyFuelEfficencyInfId;

    private String vehicleId;

    private String vehicleType;

    private Integer aggregateYearMonth;

    private Integer aggregateWeek;

    private Double fuelEfficiency;

    private int totalMilage;

    private String registDate;

    private String updateDate;
}
