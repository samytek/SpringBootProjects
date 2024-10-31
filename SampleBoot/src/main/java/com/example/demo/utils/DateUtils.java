package com.example.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.exception.ErrorHandler;

public class DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	private DateUtils() {
		
	}

	public static Date getDateFromString(final String dateString, final String format)  {
		try {
				if(dateString.equalsIgnoreCase("") || dateString == null) {
					return null;
				}
				final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				dateFormat.setLenient(false);
				return dateFormat.parse(dateString);
				
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			return null;
		}
	}

	/**
	 * Get date after adding some days into it.
	 * @param date
	 * @param noOfDays
	 * @return {@link Date}
	 */
	public static Date getDateAfterAddingDays(final Date date, final Integer noOfDays) {
		try {
			if(date ==  null) {
				return null;
			}
			final Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, noOfDays);
			return cal.getTime();
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			return null;
		}
	}
	
	/**
	 * Get difference between 2 dates
	 * @param firstDate
	 * @param secondDate
	 * @return no of days i.e. difference
	 */
	public static long dayDifference(final Date firstDate, final Date secondDate) {
		long diff = 0;
		long diffDay = 0;
		diff = secondDate.getTime() - firstDate.getTime();
		if (diff > 0) {
			diffDay = diff / (24 * 60 * 60 * 1000);
		}
		return diffDay;
	}
	
	public static Date convertStringToDate(String dateString, String format) {
		try {
			format = format == null || format == "" ? "dd/MM/yyyy" : format;
			Date date = null;
			DateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(dateString);
			} catch (Exception ex) {
				logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), ex));
			}
			return date;
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			return null;
		}
	}
	
	public static String getDateInString(String dateFormat, Date dateObj) {
		SimpleDateFormat parseDate = new SimpleDateFormat(dateFormat);
		String vp="";
		if (dateObj != null) {
			try {
				vp = parseDate.format(dateObj);
			} catch (Exception e) {
				logger.error(ErrorHandler.getErrorMessageLog(new Object() {
				}.getClass(), e));
			}

		}
		return vp;
	}

	public static Date addDaysToDate(String dateString, String format, int days) {
		
		format = format == null || format == "" ? "dd/MM/yyyy" : format;
		Date oldDate = null;
		DateFormat df = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		try {
			oldDate = df.parse(dateString);
			c.setTime(oldDate);
			// Number of Days to add
			c.add(Calendar.DAY_OF_MONTH, days);
		} catch (Exception ex) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), ex));
		}
		return c.getTime();
	}
	
	public static boolean isBoeExpired(Date boeDate,int noofDays) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // convert date to calendar
		boolean result=true;
		Date curDate=new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(boeDate);
        c.add(Calendar.DAY_OF_MONTH, noofDays);
        Date currentDatePlusOne = c.getTime();
        try {
        	Date expiryDate=dateFormat.parse(dateFormat.format(currentDatePlusOne));
			if(expiryDate.after(curDate)) {
				result=false;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e1));
		}
		return result;
	}
	
	public static java.sql.Date getTodaysSqlDate() {
		Date date = new Date();
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		String uplodDate = sqlDate.format(date);
		java.sql.Date dateformat = java.sql.Date.valueOf(uplodDate);
		return dateformat;
	}
	
	public static String getTodaysDateInString(String formater) {
		Date todayDate = new Date();
		SimpleDateFormat sqlDate = new SimpleDateFormat(formater);
		String todaysDate = sqlDate.format(todayDate);
		return todaysDate;
	}
	
	public static Date getPreviousDate(Date inputDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
	    cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static Date getNextDate(Date inputDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
	    cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
	
	public static boolean isStandardHoliday(Date inputDt) {
  		boolean isHoliday=false;
  		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
  		Calendar c = Calendar.getInstance();
  		c.setTime(inputDt);
  		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
  		int dateVal = c.get(Calendar.DATE);
  		if(dayOfWeek==Calendar.SUNDAY) {
  			isHoliday=true;
  		}else if(dayOfWeek==Calendar.SATURDAY) {
  			if((dateVal>=8 && dateVal<=14) || (dateVal>=22 && dateVal<=28)){
  	  			isHoliday=true;
  			}
  		}
  		return isHoliday;
  	}
	
	public static String formatDateToString(Date dateObject, String dateFormat) {
		String dateStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			dateStr = sdf.format(dateObject);
		} catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return dateStr;
	}
	
	public static Date parseStringToDate(String dateString, String format)  {
		Date dt = null;
		try {
			if (CommonUtils.isNullOrEmpty(dateString) == true) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			dt = sdf.parse(dateString);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return dt;
	}
	
	public static String getNextMonthofDate(String dateString, String format, String startEndDateFlag)  {
		Calendar c = Calendar.getInstance(); 

		String[] splitedDate = dateString.split("-");
		int day = Integer.parseInt(splitedDate[0]);
		int month = Integer.parseInt(splitedDate[1]);
		int year = Integer.parseInt(splitedDate[2]);

		c.set(year, month, day);
		if(startEndDateFlag.equalsIgnoreCase("fromDate")) {
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		} else {
			if(month != 1) {
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			} else {
				c.set(year, Calendar.FEBRUARY, day);
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
		String nextMonthAsString = sdf.format(c.getTime());
		logger.info("getNextMonthofStartDate : "+nextMonthAsString);
		return nextMonthAsString;
	}
	
	public static ArrayList<String> createFirstFortNightDate(String month, String year) {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("01-" + month + "-" + year + "-");
		list.add("15-" + month + "-" + year + "-");
		return list;
	}
	
	public static ArrayList<String> createSecondFortNightDate(String date, String month, String year) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("16-" + month + "-" + year + "-");
		list.add(date + "-" + month + "-" + year + "-");
		return list;
	}
	
	public static String getLastDayOfMonthAsString(int year, int month) {
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        return lastDayOfMonth.format(formatter);
    }
	
}
