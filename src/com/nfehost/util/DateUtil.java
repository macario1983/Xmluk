package com.nfehost.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat format;
	private static SimpleDateFormat formatDateHour;

	public static Date stringToDate(String str) {
		try {
			return getFormat().parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("A string da data não tem o formato definido para aplicar cast para Date", e); 
		}	
	}

	private static SimpleDateFormat getFormat() {
		if (format == null) {
			format = new SimpleDateFormat("dd/MM/yyyy");
		}
		return format;
	}
	
	/**
	 *  
	 * @return Formato "dd/mm/aaaa HH:mm"
	 */
	public static String formatDateHour(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	public static String dateHourToString(Date data) {
		return getFormatDateHour().format(data);
	}
	
	private static SimpleDateFormat getFormatDateHour() {
		if (formatDateHour == null) {
			formatDateHour = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
			formatDateHour.setLenient(false);
		}
		return formatDateHour;
	}


}
