/**
 * Copyright (C) Flipkart. All rights reserved
 */
package com.flipkart.cp.transact.toa.util.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author bhabani.panda
 * @since May 21, 2014
 */
public class DateUtil {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * Returns date after converting string to date
	 * 
	 * @return
	 */
	public static Date stringToDate(String dateStr, String format) {
		DateFormat formatter;
		Date date = new Date();
		formatter = new SimpleDateFormat(format);
		try {
			date = (Date) formatter.parse(dateStr);
			log.info("stringToDate - converted date {}", date);
		} catch (ParseException e) {
			log.info("stringToDate - Error in converting date {} {}", e, e.getMessage());
		}

		return date;
	}

	/**
	 * Converts Date to string
	 * 
	 * @param inputDate Date, String format
	 * @return String
	 */
	public static String dateToString(Date inputDate, String format) {
		String dateStr = "";
		if (inputDate != null) {
			DateFormat dateFormat = new SimpleDateFormat(format);
			dateStr = dateFormat.format(inputDate);
		}
		return dateStr;
	}

	/**
	 * Returns Formatted date for a input date and format
	 * 
	 * @return
	 */
	public static Date formatDate(Date date, String format) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String formattedDate = sdf.format(date);
			log.info("formatDate - formattedDate {}", formattedDate);

			return stringToDate(formattedDate, format);
		}
	}
	
	public static void main(String[] args) {
		String dateStr = "01-05-2020";
		Date date = stringToDate(dateStr, "dd/MM/yyyy");
		
		System.out.println(date);
	}
	
	public static boolean isValidDate(String dateString, String format){
		if(StringUtils.isEmpty(dateString)){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		try {
			sdf.parse(dateString);
		} catch (ParseException e) {
			return false;
		}
 
		return true;
	}

	public static String formatDate(Date date, String dateFormat, TimeZone tz) {
		SimpleDateFormat ft = new SimpleDateFormat(dateFormat);
		ft.setTimeZone(tz);
		return ft.format(date);
	}
}
