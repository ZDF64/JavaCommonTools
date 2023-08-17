package zdf.learn.com.commonUtils.pojo;

import java.io.Serializable;
import java.time.ZonedDateTime;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MysqlSchema(name = "vehicle_features_month_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFeaturesMonthInf implements Serializable {

	@Id
	private long vehicleFeaturesMonthInfId;

	@Column(name = "vehicle_id")
	private String vehicleId;

	@Column(name = "latest_odo_inf")
	private Integer latestOdoInf;

	@Column(name = "exist_flag")
	private String existFlag;

	@Column(name = "features01")
	private String features01;

	@Column(name = "features02")
	private String features02;

	@Column(name = "features03")
	private String features03;

	@Column(name = "features04")
	private String features04;

	@Column(name = "features05")
	private String features05;

	@Column(name = "features06")
	private String features06;

	@Column(name = "features07")
	private String features07;

	@Column(name = "features08")
	private String features08;

	@Column(name = "features09")
	private String features09;

	@Column(name = "features10")
	private String features10;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}