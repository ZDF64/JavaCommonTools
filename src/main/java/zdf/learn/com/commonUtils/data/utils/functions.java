package zdf.learn.com.commonUtils.data.utils;

import static org.apache.spark.sql.functions.udf;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataTypes;

import scala.collection.JavaConversions;
import scala.collection.mutable.WrappedArray;

/**
 * UDF functions
 */
public class functions {
    
    public static UserDefinedFunction long_to_timestamp = udf((Long lng) -> new Timestamp(lng), DataTypes.TimestampType);
    
    public static UserDefinedFunction long_to_date = udf((Long lng) -> new Date(lng), DataTypes.DateType);
    /**
     * 截断浮点
     */
    public static UserDefinedFunction float_to_intStr = udf((String fla) -> Float.valueOf(fla).intValue(), DataTypes.IntegerType);
    public static UserDefinedFunction dateToyyyyMMdd = udf((String dateStr) -> {return dateStr.replaceAll("-", "");} , DataTypes.StringType);
    public static UserDefinedFunction removeHH = udf((String dateStr) -> {return dateStr.substring(0, dateStr.length()-3);} , DataTypes.StringType);
    public static UserDefinedFunction removeHHmmss = udf((String dateStr) -> {return dateStr.substring(0, dateStr.length()-3);} , DataTypes.StringType);
    // 油门转换
    public static UserDefinedFunction throttle_transformation = udf((Double val, String throttleThreshold) -> {
        
        List<String> list = Arrays.asList(throttleThreshold.split(","));
        
        if (val == null) {
            return "NONE";
        }
        
        for (String str : list) {
            
            String[] strArr = str.split("~");
            
            double lower = Double.valueOf(strArr[0]);
            double upper = Double.valueOf(strArr[1]);
            
            if(val > lower && val <= upper) {
                return str;
            }
            
        }
        
        return "NONE";
    }, DataTypes.StringType);
    
    // 速度转换
    public static UserDefinedFunction speed_transformation = udf((Double val, String velocityThreshold) -> {
        
        List<String> list = Arrays.asList(velocityThreshold.split(","));
        
        if (val == null) {
            return "NONE";
        }
        
        for (String str : list) {
            
            String[] strArr = str.split("~");
            
            double lower = Double.valueOf(strArr[0]);
            double upper = Double.valueOf(strArr[1]);
            
            if(val > lower && val <= upper) {
                return str;
            }
            
        }
        
        return "NONE";
    }, DataTypes.StringType);
    
    public static UserDefinedFunction labelSplit= udf((String label, Object value, String labelName) -> labelName.equals(label)?value.toString():null, DataTypes.StringType);
    
    public static UserDefinedFunction isEmpty= udf((WrappedArray<String> value) -> value.length() > 0 ? value : null, DataTypes.createArrayType(DataTypes.StringType));
    
    public static UserDefinedFunction brakesFilter= udf((String value, String lastValue) -> !("1".equals(value) && "1".equals(lastValue)) ? value : null, DataTypes.StringType);
    
    public static UserDefinedFunction array_sum= udf((WrappedArray<Object> value) -> {
        
        if (value == null) {
            return 0D;
        }
        
        BigDecimal rs = new BigDecimal(0D);
        
        for (Object v : JavaConversions.seqAsJavaList(value.toSeq())) {
            if (v != null) {
                rs = rs.add(new BigDecimal(v.toString()));
            }
        }
        
        return rs.doubleValue();
        
    }, DataTypes.DoubleType);

    public static UserDefinedFunction array_zero_count= udf((WrappedArray<Object> value) -> {

        if (value == null) {
            return 0D;
        }

        BigDecimal rs = new BigDecimal(0D);

        for (Object v : JavaConversions.seqAsJavaList(value.toSeq())) {
            if (v.toString().equals("0.0")) {
                rs = rs.add(new BigDecimal(1));
            }
        }

        return rs.doubleValue();

    }, DataTypes.DoubleType);
    
}
