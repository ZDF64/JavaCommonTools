package zdf.learn.com.commonUtils.data.spark;

import static org.apache.spark.sql.functions.udf;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataTypes;

public class SparkUdfs {

	public static UserDefinedFunction makeVin = udf((String vin) -> {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(vin.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
			}
			return result.substring(0, 5).toUpperCase() + vin.substring(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}, DataTypes.StringType);
	public static UserDefinedFunction dateToyyyyMMdd = udf((Date date) -> {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(date);
	}, DataTypes.StringType);
	public static UserDefinedFunction long_to_date = udf((Long lng) -> new Date(lng), DataTypes.DateType);
	public static UserDefinedFunction toFullTargetDate = udf((Integer tar) -> {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tarStr = tar + "";
		String targetDate = tarStr.substring(0, 4) + "-" + tarStr.subSequence(4, 6) + "-" + tarStr.substring(6, 8);
		return new java.sql.Date(simpleDateFormat.parse(targetDate).getTime());
	}, DataTypes.DateType);
}
