package zdf.learn.com.commonUtils.data;

import static org.apache.spark.sql.functions.*;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataTypes;

import scala.collection.JavaConverters;

/**
 * UDF functions
 */
public class functions {
    
    public static UserDefinedFunction string_time_to_long = udf((String str) -> new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(str, new ParsePosition(0)).getTime(), DataTypes.LongType);
    
    public static UserDefinedFunction local_date_time_to_long = udf((String str) -> Timestamp.valueOf(LocalDateTime.parse(str)).getTime(), DataTypes.LongType);
    
    public static UserDefinedFunction long_to_timestamp = udf((Long lng) -> new Timestamp(lng), DataTypes.TimestampType);
    
    public static UserDefinedFunction timestamp_to_long = udf((Timestamp t) -> t.getTime(), DataTypes.LongType);
    
    public static UserDefinedFunction date_format_long = udf((Long lng, String fmt) -> new SimpleDateFormat(fmt).format(new Date(lng)), DataTypes.StringType);
    
    public static UserDefinedFunction long_to_date = udf((Long lng) -> new Date(lng), DataTypes.DateType);
    
    /**
     * convert various format of time to long value
     */
    private static final DateTimeFormatter dtf1 = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
    private static final DateTimeFormatter dtf2 = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss").toFormatter();
    private static final DateTimeFormatter dtf3 = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss.").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
    private static final DateTimeFormatter dtf4 = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter();
    private static final DateTimeFormatter dtf5 = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss.").appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
    
    private static final Map<String, DateTimeFormatter[]> fmtMap = new HashMap<>();
    static {
        fmtMap.put("request", new DateTimeFormatter[] {dtf1, dtf5});
        fmtMap.put("gps", new DateTimeFormatter[] {dtf2, dtf4});
        fmtMap.put("outside", new DateTimeFormatter[] {dtf3, dtf5});
        fmtMap.put("type3", new DateTimeFormatter[] {dtf3, dtf5});
    }
    
    public static final BiFunction<String, String, Long> toLongTime = (inTime, type) -> {
        
        Long result = null;
        
        for (DateTimeFormatter fmt : fmtMap.get(type)) {
            try {
                result = Timestamp.from(LocalDateTime.parse(inTime, fmt).atZone(ZoneId.systemDefault()).toInstant()).getTime();
            } catch (Exception e) {
            }
            if (result != null) {
                break;
            }
        }
        
        if (result == null) {
            throw new RuntimeException();
        }
        
        return result;
        
    };
    
    public static UserDefinedFunction datetime_to_long = udf((String str, String type) -> toLongTime.apply(str, type), DataTypes.LongType);
    
    /**
     * if o is a map, return o
     * otherwise, return map{o: null}
     */
    public static UserDefinedFunction toMapKeyOnly = udf(o -> {
        if (o instanceof scala.collection.immutable.Map) {
            return o;
        }
        return JavaConverters.mapAsScalaMapConverter(Collections.singletonMap(o, null)).asScala();
    }, DataTypes.createMapType(DataTypes.StringType, DataTypes.StringType));
    
}
