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
@MysqlSchema(name = "message_features_master_inf")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageFeaturesMasterInf implements Serializable {

	@Id
	private long message_features_master_inf_id;

	@Column(name = "features_id")
	private String featuresId;

	@Column(name = "message_id")
	private String messageId;

	@Column(name = "message_union_id")
	private String messageUnionId;

	@Column(name = "regist_date")
	private ZonedDateTime registDate;

	@Column(name = "update_date")
	private ZonedDateTime updateDate;
}