package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;

@SuppressWarnings("serial")
@MysqlSchema(name = "dlr_vehicle_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DlrVehicleMonthInf implements Serializable {

	@Id
	private long dlrVehicleMonthInfId;

	@Column(name = "dlr_code")
	private String dlrCode;

	@Column(name = "vehicle_id")
	private String vehicleId;

	@Column(name = "model")
	private String model;

	@Column(name = "type_flg")
	private String type_flg;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}