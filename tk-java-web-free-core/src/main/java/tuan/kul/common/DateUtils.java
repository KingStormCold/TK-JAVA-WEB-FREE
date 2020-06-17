package tuan.kul.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
    public final static String FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_YYMMDDHHMMSS = "ddMMyyHHmmss";
    public static final Pattern PATTERN_DATE = Pattern
			.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)+$");
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    public static final String MY_TIME_ZONE = "Asia/Bangkok";

    public String convertDateToString(String format, Date date) {
        if (date == null) {
            return "";
        }
        String result = "";
        SimpleDateFormat simpleDateFormat;
        switch (format) {
        case FORMAT_YYYY_MM_DD_HHMMSS:
            simpleDateFormat = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
            result = simpleDateFormat.format(date);
            return result;
        case FORMAT_YYMMDDHHMMSS:
            simpleDateFormat = new SimpleDateFormat(FORMAT_YYMMDDHHMMSS);
            result = simpleDateFormat.format(date);
            return result;
        default:
            return null;
        }
    }
    
    public static Date convertStringToDate(String date) {
		try {
			if (PATTERN_DATE.matcher(date).matches()) {
	    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setTimeZone(UTC_TIME_ZONE);
				df.setLenient(false);
				return df.parse(date);
			}
		} catch (ParseException e) {
			
		}
		return null;
	}
}
