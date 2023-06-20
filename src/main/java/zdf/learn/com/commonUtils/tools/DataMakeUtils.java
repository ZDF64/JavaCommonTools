package zdf.learn.com.commonUtils.tools;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class DataMakeUtils {
	/**
	 * 当前时间串
	 */
	public Function<String,String> createDateStringByFormat = (format)->{
		return DateTimeFormatter.ofPattern(format).format(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
	};
	public Function<String,Long> localDateTimeStrToTimeStamp = (ldtStr)->{
		return LocalDateTime.parse(ldtStr.replace(" ", "T")).toInstant(ZoneOffset.of("+8")).toEpochMilli();
	};
	public Supplier<String> createDateString = ()->{
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
	};
	public Function<String,String> createDateAfterString = (current)->{
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.parse(current).minusHours(1).minusMinutes((int)Math.random()*30));
	};
	public Supplier<LocalDateTime> createDateTime = ()->{
		return LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
	};
	public Supplier<LocalDateTime> createDateAfterNowTime = ()->{
		return LocalDateTime.now(ZoneId.of("Asia/Shanghai")).minusHours(1).minusMinutes((int)(Math.random()*30));
	};
	public Function<String,LocalDateTime> createDateAfterTime = (current)->{
		return LocalDateTime.parse(current).minusHours(1).minusMinutes((int)(Math.random()*30));
	};
	public Supplier<String> makeVin = ()->{
		return String.format("VINFEW%03dB00%04d",(int)(Math.random()*1000),(int)(Math.random()*10000));
	};
	public Function<String,String> makeVinByEncrypt = (vin)-> {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(vin.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
			}
			return result.substring(0,5) + vin.substring(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	};
	public Supplier<Integer> makeInt = ()->{
		return (int)(Math.random()*1000);
	};
	/**
	 * 生成16进制数
	 */
	public Supplier<String> makeHexStr = ()->{
		int n = (int)(Math.random()*10000);
		StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;            
        }
        a = sb.reverse().toString();
		return a;
	};
	public Function<Integer,String> makeHexStrByMax = (max)->{
		int n = (int)(Math.random()*max);
		StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        if(n==0) {
        	sb = sb.append("0");
        }
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;            
        }
        a = sb.reverse().toString();
		return a;
	};
	public Supplier<String> makeBase = ()->{
		switch (((int)Math.random()*10)%5) {
		case 0:
			return "TMCI";
		case 1:
		case 2:
			return "GTMC";
		case 3:
		case 4:
			return "FTMS";
		default:
			return "BTET";
		}
	};
	public Supplier<String> makeSettingId = ()->{
		switch (((int)Math.random()*10)%5) {
		case 0:
			return "301";
		case 1:
		case 2:
			return "108";
		case 3:
		case 4:
			return "200";
		default:
			return "100";
		}
	};
	public Supplier<String> makeSettingValue = ()->{
		return String.format("%.2f", Math.random()*90);
	};
	public static void main(String[] args) {
		DataMakeUtils dmu = new DataMakeUtils();
		LongStream.iterate(0, x->x+1).limit(10000).forEach(cos->{
			System.out.println(dmu.makeHexStrByMax.apply(255)+",");
		});
		
	}
}
