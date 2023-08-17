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
@MysqlSchema(name = "dst_company_master_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DstCompanyMasterInf implements Serializable {

	@Id
	private long dstCompanyMasterInfId;

	@Column(name = "company_code")
	private String companyCode;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "short_company_name")
	private String shortCompanyName;

	@Column(name = "report_name")
	private String reportName;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}