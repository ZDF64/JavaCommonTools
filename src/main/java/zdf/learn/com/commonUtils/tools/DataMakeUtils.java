package zdf.learn.com.commonUtils.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Supplier;

public class DataMakeUtils {
	/**
	 * 当前时间串
	 */
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
	
}
