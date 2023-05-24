package zdf.learn.com.commonUtils.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.text.DateFormatter;

public class TimeParserTools {
	public static void main(String[] args) throws ParseException {
		String timeStr = "Mon, 01 May 2023 16:00:00 GMT";
//		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US); 
//		
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));  
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
		LocalDateTime inputDate = LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH));
		
		System.out.println(inputDate);
	}
}
