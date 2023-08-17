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
@MysqlSchema(name = "modeladditional_master_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeladditionalMasterInf implements Serializable {

	@Id
	private long modeladditionalMasterInfId;

	@Column(name = "model")
	private String model;

	@Column(name = "model_desc")
	private String modelDesc;

	@Column(name = "car_series")
	private String carSeries;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}