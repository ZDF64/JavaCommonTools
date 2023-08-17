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
@MysqlSchema(name = "companyadditional_master_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyadditionalMasterInf implements Serializable {

	@Id
	private long companyadditionalMasterInfId;

	@Column(name = "company_code")
	private String companyCode;

	@Column(name = "province")
	private String province;

	@Column(name = "area_code")
	private String areaCode;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}