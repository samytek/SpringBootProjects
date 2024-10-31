package com.example.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ErrorHandler Utility.
 * 
 * Contains static getErrorMessage(Exception e) method. 
 *
 */
public class ErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	/**
	 * Accepts an exception object and returns its stack trace in string format.
	 * 
	 * @param ex
	 * 		Any exception object
	 * @return
	 * 		String representation of detailed stacktrace
	 */
	public synchronized static String getErrorMessage(Exception ex) {
		StringWriter stringWritter = new StringWriter();
		PrintWriter printWritter = new PrintWriter(stringWritter, true);
		ex.printStackTrace(printWritter);
		printWritter.flush();
		stringWritter.flush();
		return "Exception::::::" + stringWritter.toString();
	}
	/**	
	 * @param ex
	 *        Any exception object
	 * @return String as a standard pattern to print log information.
     * e.g. Error occurred at <<module name>> - <<method name>> due to <<cause for error>> - <<16 digit random number>>
	 */
	public static String getErrorMessageLog(Class<?> classObject, Exception e) {
		String nameofClass = "";
		String nameofMethod = "";
		try {	
			nameofClass = classObject.getName();
			nameofMethod = classObject.getEnclosingMethod().getName(); 
		} catch (Exception ex) {
			logger.error("Not able to get Class/Method Name: "+e);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Error occurred at ");		
		sb.append("<<"+nameofClass+">>");
		sb.append(" - Method @ ");
		sb.append("<<"+nameofMethod+">>");
		sb.append(" - ");
		sb.append("<<"+getRandomNumber()+">>");
		sb.append(" - ");
		sb.append(" due to ");
		sb.append("<<"+getErrorMessage(e)+">>");		
		return sb.toString();
	}
	
	 
	/**
	 * Generate this 16 digit random number
	 * @return
	 */
	public static long getRandomNumber() {
		long number = (long) Math.floor(Math.random() * 9000000000000000L) + 1000000000000000L;
		return number;
	} 
}
