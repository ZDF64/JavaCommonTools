package zdf.learn.com.commonUtils.pojo;

import zdf.learn.com.commonUtils.annotation.MysqlSchema;
import zdf.learn.com.commonUtils.annotation.MysqlSchema.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@MysqlSchema(name = "kpirate")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiRatePojo implements Serializable {

	@Id
	private String id;//TODO 为了配合JDBCUtil使用 只能设置一个假的ID属性


	@Column(name="dlrcode")
	private String dlrcode;

	@Column(name="dateline")
	private int dateline;

	@Column(name="expectcnt")
	private int expectcnt;

	@Column(name="factcnt")
	private int factcnt;

	@Column(name="userate")
	private double userate;

	@Column(name="createdOn")
	private Date createdOn;
}
