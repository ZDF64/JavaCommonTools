package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "order_histroy_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistroyMonthInf implements Serializable {

	@Id
	private long orderHistroyMonthInfId;

	@Column(name = "dlr_code")
	private String dlrCode;

	@Column(name = "vehicle_id")
	private String vehicleId;

	@Column(name = "model")
	private String model;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}
