package zdf.learn.com.commonUtils.data.avro.schema;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaTripPojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5763306079970875166L;
	private String vehicle_id;
	private String target_date;
	private String igOn;
	private String igOff;
}
