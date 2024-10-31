package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class CommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	private static final String NULLVALUE = "null";	
	
	private CommonUtils() { }

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(final Object object) {
		if (object == null) {
			return true;
		}
		
		if (object.equals(NULLVALUE)) {
			return true;
		}
		
		if (object instanceof String) {
			return StringUtils.isBlank((String) object);
		}
		
		if (object instanceof List) {
			return ((List) object).isEmpty();
		}
		
		if (object instanceof Map) {
			return ((Map) object).size() <= 0;
		}
		
		if (object instanceof ArrayNode) {
			return ((ArrayNode) object).size() == 0;
		}
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotNullAndNotEmpty(final Object object) {
		if (object != null) {
			if (object instanceof List) {
				return !((List) object).isEmpty();
			}
			if (object instanceof String) {
				if (StringUtils.equals((String) object, NULLVALUE)) {
					return false;
				} else if (StringUtils.isBlank((String) object)) {
					return false;
				} else {
					return true;
				}
			}
			if (object instanceof Map) {
				return ((Map) object).size() > 0;
			}
			if (object instanceof ArrayNode) {
				return ((ArrayNode) object).size() > 0;
			}
			return true;
		}
		return false;
	}
 
	public static String getStringValue(String newString, String altString) {
		String returnString = altString;
		if ( CommonUtils.isNotNullAndNotEmpty(newString) )
				returnString = newString;
		return returnString;
	}
	
	public static Date getMonthEndDate(Date txnDate) {
		if(CommonUtils.isNotNullAndNotEmpty(txnDate)) {
			txnDate = clearTime(txnDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(txnDate);
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
			return cal.getTime();
		}
		return txnDate;
	}
		
	public static int getIntValue(final Object object, int defaultValue) {
		int returnValue = defaultValue;
		if ( object!= null ) {
			if (object instanceof String) {
				if( ! ((String)object).equals("") )
					returnValue = Integer.parseInt((String) object);
			}
		}
		return returnValue;
	}
	
	public static String removeUnwantedCharactersAndExtraSpace(String str, String regrex)
	{  
		if (isNullOrEmpty(str) == true) {
			str = "";
			return str;
		}
		str = str.trim().replaceAll(regrex, ""); 
		return str.replaceAll(" +", " ");
	} 
	
	public static String removeSpecialCharacterFunction(String str, String regrex)
	{  
		if (isNullOrEmpty(str) == true) {
			str = "";
			return str;
		}
		str = str.trim().replaceAll(regrex, ""); 
		return str;
	} 
	
	public static String removeSpecialCharacterStoreRegex(String str, String regex)
	{  
		if (isNullOrEmpty(str) == true) {
			return str;
		}
		str = str.replaceAll(","," ").replaceAll(regex, ""); 
		return str;
	} 
	public static String removeSpecialCharacterAndprefixesByLimit(String str, String regrex, int limit)
	{  
		if (isNullOrEmpty(str) == true) {
			str = "";
			return str;
		}
		str = str.trim().replaceAll(regrex, ""); 
		if(str.length() > limit) {
			str = str.substring(str.length() - limit);
//			str = str.substring(0, limit);
		} else if ((str.length() < 5)) {
			String result = StringUtils.leftPad(str, 5, "0");
			return result;
		}
		return str;
	} 
	public static String removeSpecialCharacterAndSubStrByLimit(String str, String regrex, int limit)
	{  
		if (isNullOrEmpty(str) == true) {
			return str;
		}
		str = str.trim().replaceAll(regrex, ""); 
		if(str.length() > limit) {
			str = str.substring(str.length() - limit);
//			str = str.substring(0, limit);
		}  
		return str;
	} 
	public static void moveFile(String src, String dest) {
		try {
			if(!new File(dest).getParentFile().exists()) {
				new File(dest).getParentFile().mkdirs();
			}
			Files.move(Paths.get(src), Paths.get(dest));
		} catch (IOException e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
	}

	public static Date clearTime(Date dateObj) {
		Date date = null;
		try {
			date = DateUtils.truncate(dateObj, Calendar.DATE);
		} catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return date;
	}

	public static Date add23Hours59Minutes59Seconds(Date dateObj) {
		Date date = null;
		try {
			date = DateUtils.addMilliseconds(DateUtils.addDays(clearTime(dateObj), 1), -1);
		} catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return date;
	}

	public static Date addDays(Date dateObj, int numberOfDays) {
		Date date = null;
		try {
			date = DateUtils.addDays(clearTime(dateObj), numberOfDays);
		} catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return date;
	}

	public static Date addMonths(Date dateObj, int numberOfDays) {
		Date date = null;
		try {
			date = DateUtils.addMonths(clearTime(dateObj), numberOfDays);
		} catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return date;
	}

	public static boolean compareDate(Date dateObj) {
		boolean status = false;
		try {
			Date futureValueDate = dateObj;
			Date todayDate = new Date();
			if (futureValueDate.after(todayDate)) { 
				status = true;
			}
		}catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return status;
	}

	public static String getUpperNoWhiteSpaceStringFromExcelCell(Cell cellInst) {
		String result = "";
		if(cellInst != null) {
			result = noWhiteSpace((cellInst.toString()==null)?"":cellInst.toString().trim().toUpperCase());
		}
		return result;
	}
	
	public static String getUpperStringFromExcelCell(Cell cellInst) {
		String result = "";
		if(cellInst != null) {
			DataFormatter dataFormatter = new DataFormatter();
	        result = dataFormatter.formatCellValue(cellInst);			
			//result = cellInst.toString().toUpperCase();
			result = result.replace("&", " AND ");
			result = result.replaceAll("\\s+"," ");
			result = result.trim();
			if ( result.equals(" ")) result="";
		}
		return result;
	}
	
	public static String getUpperStringFromExcelCellData(Cell cellInst) {
		String result = "";
		if(cellInst != null) {
			DataFormatter dataFormatter = new DataFormatter();
	        result = dataFormatter.formatCellValue(cellInst);			
			//result = cellInst.toString().toUpperCase();
			//result = result.replace("&", " AND ");
			result = result.replaceAll("\\s+"," ");
			result = result.trim();
			if ( result.equals(" ")) result="";
		}
		return result;
	}
	
	public static String noWhiteSpace(String inputString) {
		String outputString = inputString;
			outputString = inputString.replaceAll("\\s", "");
		return outputString ;
	}


	public static String getStringFromExcelCell(Cell cellInst) {
		String result = "";
		if(cellInst != null) {
			DataFormatter dataFormatter = new DataFormatter();
	        result = dataFormatter.formatCellValue(cellInst);
//			result = cellInst.toString();
			result = result.replace("&", " AND ");
			result = result.replaceAll("\\s+"," ");
			result = result.trim();
			if ( result.equals(" ")) result="";
		}
		return result;
	}
	
	public static String convertExponentialObjToString(Cell cellInst) {
		String accountNumber = "";
		if (cellInst != null) {
			DataFormatter dataFormatter = new DataFormatter();
			accountNumber = dataFormatter.formatCellValue(cellInst);
			accountNumber = accountNumber.replace(".00", "");
			accountNumber = accountNumber.trim();
			// below code only exponential cell values
			// Double doubleValue = cellInst.getNumericCellValue();
			// BigDecimal bd = new BigDecimal(doubleValue.toString());
			// long lonVal = bd.longValue();
			// accountNumber = Long.toString(lonVal).trim();
			// System.out.print("accountNumber " + accountNumber);
		}
		return accountNumber;
	}

	public static String getCleanOrEmptyString(String inString) {
		String result = "";
		if(inString != null) {
			result = inString;
			result = result.replace("&", " AND ");
			result = result.replaceAll("\\s+"," ");
			result = result.trim();
			if ( result.equals(" ")) result="";
		}
		return result;
	}
	
	public static String fetchLastTenChar(String lastTenCharCif) {
		String lastTenChar = "";
		if (lastTenCharCif.length() > 10) {
			lastTenChar = lastTenCharCif.substring(lastTenCharCif.length() - 10);
		} else {
			lastTenChar = lastTenCharCif;
		}
		return lastTenChar;
	}
	public static String trimLpeNatureOfBusiness(String occupation) {
		String reOccupation = "";
		if (occupation.length() > 45) {
			reOccupation = occupation.substring(occupation.length() - 45);
		} else {
			reOccupation = occupation;
		}
		return reOccupation;
	}
	
	
	public static int getIntFromExcelCell(Cell cellInst) {
		int result = 0;
		String stringVal = "";
		if(cellInst != null) {
			stringVal = (cellInst.toString()==null)?"":cellInst.toString().trim();
		}
		if(!stringVal.isEmpty()) {
			result = Double.valueOf(stringVal).intValue();
		}
		return result;
	}

	public static BigDecimal getBigDecimalFromExcelCell(Cell cellInst) {
		BigDecimal result = null;
		String stringVal = "";
		if(cellInst != null) {
			stringVal = (cellInst.toString()==null)?"":cellInst.toString().trim();
		}
		if(!stringVal.isEmpty()) {
			result = new BigDecimal(stringVal);
		}
		return result;
	}
	
	public static Date getDateObj(String existingDateFormat, String expectedDateFormat, String date) {
		Date valP = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(existingDateFormat);
			SimpleDateFormat parseDate = new SimpleDateFormat(expectedDateFormat);
			Date validityPeriod = sdf.parse(date);
			String vp = parseDate.format(validityPeriod);
			valP = parseDate.parse(vp);
			return valP;
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			return valP = null;
		}
	}

	//Solution for all date format issue //
//	public static Date getFiuFormatDateForAllDateFormat(String expectedDateFormat, String date) {
//		
//		Date expectedDate = null;
//		List<String> formatStringsList = Arrays.asList("dd-MM-yyyy","yyyy-MM-dd","dd-MM-yyyy","dd-MMM-yyyy","dd/MMM/yyyy","dd/MM/yyyy","yyyy/MM/dd");
//	    for(String format: formatStringsList){
//	        SimpleDateFormat sdf = new SimpleDateFormat(format);
//			SimpleDateFormat parseDate = new SimpleDateFormat(expectedDateFormat);
//
//	        try{
//	        	Date validityPeriod = sdf.parse(date);
//				String vp = parseDate.format(validityPeriod);
//				expectedDate = parseDate.parse(vp);
//				return expectedDate;
//	        } catch (ParseException e) {
//	        }
//	    }
//		return expectedDate;
//	}

	
	public static String convertContactNumberToRbiFormat(String input, int totalDigit, String regrex) {
		String lastTenNumber="";
		try {
			if(CommonUtils.isNullOrEmpty(input)) {
				return "";
			}
			String[] splitted = (input.split(","));

			if (splitted.length > 0) {
				String lastValue = splitted[splitted.length - 1];
				String contactNumber = lastValue.replaceAll(regrex, "");
				if (contactNumber.length() > 20) {
					if (contactNumber.length() > totalDigit) {
						lastTenNumber = contactNumber.substring(0, 11);
						return lastTenNumber;
					}
				} else if (contactNumber.length() < 11) {
					String result = StringUtils.leftPad(contactNumber, totalDigit, "0");
					return result;
				} else {
					return contactNumber;
				}
			}
		} catch (Exception e) {
			return input;
		}
		return lastTenNumber;
	}
	
//
//	public static String convertContactNumberToRbiFormat(String input, int totalDigit, String regrex) {
//		try {
//			if(isNullOrEmpty(input)) {
//				return "";
//			}		
//			String[] splitted = Arrays.stream(input.replaceAll(regrex, "").split(","))
//					  .map(String::trim)
//					  .toArray(String[]::new);
//			if(splitted.length > 0) {
//				int number =  0;
//				String lastValue = splitted[splitted.length-1];
//				int lastValLength = lastValue.length();			
//				if(lastValLength == totalDigit) {
//					//logger.info("convertContactNumberToProperStr Value equal: "+lastValue);
//					return lastValue;
//				} else if(lastValLength < totalDigit) {
//					number = totalDigit - lastValLength ;				
//					String result = StringUtils.leftPad(lastValue, totalDigit, "0");
//					//logger.info("convertContactNumberToProperStr Less Value: "+result);
//					return result;
//				} else if(lastValLength > totalDigit) {
//					number = lastValLength - totalDigit ;
//					String result = lastValue.substring(number);
//					//logger.info("convertContactNumberToProperStr More Value: "+result);
//					return result;
//				} else {
//					return input;
//				}
//			}
//		} catch (Exception e) {
//			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
//			return input;
//		}
//		return input;
//	}
	
	public static String convertMobileNumberToRbiFormat(String input, int totalDigit, String regrex) {
		String firstTenNumber="";
		try {
			if(CommonUtils.isNullOrEmpty(input)) {
				return "";
			}
			String[] splitted = (input.split(","));

			if (splitted.length > 0) {
				String firstValue = splitted[0];
				String finalMobileNOValue = firstValue.replaceAll(regrex, "");
				if (finalMobileNOValue.length() > 20) {
					if (finalMobileNOValue.length() > totalDigit) {
						firstTenNumber = finalMobileNOValue.substring(0, 10);
						return firstTenNumber;
					}
				} else if (finalMobileNOValue.length() < 10) {
					String result = StringUtils.leftPad(finalMobileNOValue, totalDigit, "0");
					return result;
				} else {
					return finalMobileNOValue;
				}
			}
		} catch (Exception e) {
			return input;
		}
		return firstTenNumber;
	}
	
	
//		String firstTenNumber="";
//		try {
//			if(isNullOrEmpty(input)) {
//				return "";
//			}		
//			String[] splitted = Arrays.stream(input.replaceAll(regrex, "").split(",")).map(String::trim)
//					.toArray(String[]::new);
//			if (splitted.length > 0) {
//				String lastValue = splitted[0];
//				firstTenNumber = lastValue.substring(0, 10);
//				// int firstTenNumberInInt = Integer.parseInt(firstTenNumber);
//				if (firstTenNumber.length() == totalDigit) {
//					return firstTenNumber;
//				} else if (firstTenNumber.length() < totalDigit) {
//					// String str3 = String.valueOf(firstTenNumberInInt);
//					String result = StringUtils.leftPad(firstTenNumber, totalDigit, "0");
//					return result;
//				} else {
//					return firstTenNumber;
//				}
//			}
//		} catch (Exception e) {
//			return input;
//		}
//		return firstTenNumber;
//	}


	public static String removeEmailByExceedLength(String inputEmail, int size) {
		inputEmail = inputEmail.replaceAll(" ", "").replaceAll("\\s+", "");//remove white spaces
		if(inputEmail != null && !inputEmail.equalsIgnoreCase("")) {
			while(inputEmail.length() > size) {
		        if (inputEmail.length() > size ) {
		        	inputEmail = inputEmail.substring(0, inputEmail.lastIndexOf(",")); 
		        }
		    }
		}
	  	   return inputEmail;
	}

	public static String trimDataBySpecifiedLength(String stringData, int lengthLimit) {
		int lenOfStringData = stringData.length();
		if(lenOfStringData > lengthLimit) {
			stringData = stringData.substring(0,  Math.min(stringData.length(), lengthLimit));
		}
		return stringData;
	}
	
	public static String trimDataBySpecifiedLengthFromRight(String stringData, int lengthLimit) {
		int lenOfStringData = stringData.length();
		if(lenOfStringData > lengthLimit) {
			stringData = stringData.substring(stringData.length() - lengthLimit);
		}
		return stringData;
	}
	
	public static String trimDataFromLeftWithSpecifiedLength(String stringData, int lengthLimit) {
		int lenOfStringData = stringData.length();
		if(lenOfStringData > lengthLimit) {
			stringData = stringData.substring(lenOfStringData - lengthLimit,  lenOfStringData);
		}
		return stringData;
	}
	
	public static JSONObject convertEntityObjectToJsonObject(Object object) {
		String jsonStr = null;
		JSONObject jsonObject = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonStr = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {					
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		if(isNotNullAndNotEmpty(jsonStr)) {
			jsonObject = new JSONObject(jsonStr);	
		}
		return jsonObject;
	}
	
	public static ArrayList<JSONObject> convertEntityListToJsonList(List<?> entityList) {
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
		for (Object object: entityList) {
			JSONObject jsonObject = null;
			try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = mapper.writeValueAsString(object);
				if(isNotNullAndNotEmpty(jsonStr)) {
					jsonObject = new JSONObject(jsonStr);
				}
			} catch (JsonProcessingException e) {					
				logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			}
			if(isNotNullAndNotEmpty(jsonObject)) {
				jsonList.add(jsonObject);
			}
		}
		
		return jsonList;
	}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static BiFunction<List,Integer,List> splitter = (list2, count)->{
	         //temporary list of lists
	         List<List> listOfLists=new ArrayList<>();
	         //helper implicit recursive function
	         BiConsumer<Integer,BiConsumer> splitterHelper = (offset, func) -> {
	             if(list2.size()> offset+count){
	                 listOfLists.add(list2.subList(offset,offset+count));
	                 //implicit self call
	                 func.accept(offset+count,func);
	             } else if(list2.size()>offset){
	                 listOfLists.add(list2.subList(offset,list2.size()));

	                 //implicit self call
	                 func.accept(offset+count,func);
	             }
	         };
	         //pass self reference
	         splitterHelper.accept(0,splitterHelper);
	         return listOfLists;
	     };

	
	     public static String addSuffixHyphenSymbolToStringValue(String input, int sizeLimit) {
	    	 if(input!=null && !input.isEmpty()) {
	    		 input =  getCleanOrEmptyString(input);
	    		 if(input.length() < sizeLimit) {
	    			 while(input.length() < sizeLimit) {
	    				 input = input+"-";
	    			 }
	    		 }
	    	 }
	    	 return input;
	     }

	     public static String[] getStringArray(List<String> arrayList) 
	     { 
	         String str[] = new String[arrayList.size()]; 
	         for (int j = 0; j < arrayList.size(); j++) { 
	             str[j] = arrayList.get(j); 
	         } 
	         return str; 
	     } 
	     public static double parseDouble(String strNumber) {
	    	   if (strNumber != null && strNumber.length() > 0) {
	    	       try {
	    	          return Double.parseDouble(strNumber);
	    	       } catch(Exception e) {
	    	          return -1;
	    	       }
	    	   }
	    	   else return 0;
	    	}
	     
	     public static void deleteAllDownloadFile(String outPutfilePath) {
	    	 
	    		File file = new File(outPutfilePath);
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isFile() && f.exists()) {
						f.delete();
					} else {
						logger.info("Cant delete a file ");
					}
				}
	    	 
	     }

	public static String dateConvertFormat(String dateStr) {
		String resultStr = "";
		if (dateStr != null && dateStr.length() > 0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				java.util.Date varDate = dateFormat.parse(dateStr);
				dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				resultStr = dateFormat.format(varDate);

			} catch (Exception e) {
				logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			}
		}
		return resultStr;
	}
	
	public static String getDateInStringFormat(String dateFormat, Date dateObj) {
		SimpleDateFormat parseDate = new SimpleDateFormat(dateFormat);
		String stringDate = "";
		try {
			stringDate = parseDate.format(dateObj);	
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return stringDate;
	}
	
	public static Date parseStringToDateFormat(String dateString, String format)  {
		Date dt = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			dt = sdf.parse(dateString);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return dt;
	}
	
	public static String getRegisterNumTwenty(String registerNum) {
		String outRegisterNum = "";
		if (registerNum != null) {
			if (registerNum.length() > 20) {
				outRegisterNum = registerNum.substring(0, 20);

			}
		} else {
			outRegisterNum = registerNum;

		}

		return outRegisterNum;
	}
	
    public static int getIndexFromString(String alphabet) {
        int result = 0; 
        for (int i = 0; i < alphabet.length(); i++) { 
            result *= 26; 
            result += alphabet.charAt(i) - 'A' + 1; 
        }
        return result;
    }
    
    public static int[] getExcelCellCoordinates(String cellTag) {
        int[] indexes = {0, 0};
        String col = cellTag.replaceAll("[^a-zA-Z]+", "");
        String row = cellTag.replaceAll("[^0-9]+", "");
        indexes[0] = Integer.parseInt(row) - 1;
        indexes[1] = getIndexFromString(col) - 1;
        return indexes;
    }

	public static String getMessageContentsFromFile(String filePath) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Reading Message Contents");
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[10];
			while (fis.read(buffer) != -1) {
				sb.append(new String(buffer));
				buffer = new byte[10];
			}
			fis.close();
		}catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Finished Reading Message Contents. total Characters :: " +sb.toString().trim().length());
		return sb.toString().trim();
	}

	public static ArrayList<Byte> fileToByteArray(File file) throws IOException {
		logger.info("input file for download :: "+file.getAbsolutePath());
		FileInputStream fileInputStream = null;
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = 0;
		ArrayList<Byte> bArray = new ArrayList<Byte>();
		byte[] bFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			for (i = 0; i < bFile.length; i++) {
				bArray.add(bFile[i]);
				sb.append(j);
				if (i != bFile.length - 1)
					sb.append(",");
			}
		} catch (FileNotFoundException e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return bArray;
	}

	public static Date getDateFromString(String date, String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return null;
	}
	public static JSONObject add(JSONObject responseJSONObj, String status,String statusCode , String message) {
		responseJSONObj.put(AppConstants.STATUS, status);
		responseJSONObj.put(AppConstants.STATUS_CODE, statusCode);
		responseJSONObj.put(AppConstants.MESSAGE, message);
		return responseJSONObj;
	}
	
	
    public static Date getQuarterEndDate(Date txnDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(txnDate);
        cal.add(Calendar.MONTH, 2);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
 
	public static String removeSpecialCharacterAndTrim(String str, String regrex, int limit) {
		if (isNullOrEmpty(str) == true) {
			return str;
		}
		str = str.trim().replaceAll(regrex, "");
		if (str.length() > limit) {
			str = str.substring(0, limit);
		}
		return str;
	}
    

	public static List<String> generateKeysForExcel(Row row, boolean isHeaderAvailable){
		List<String> keyList = new ArrayList<String>();
		int cellsCount = row.getLastCellNum();
		for(int i = 0 ; i < cellsCount; i++) {
			if(isHeaderAvailable) {
				keyList.add(CommonUtils.getStringContentFromExcelCell(row.getCell(i)));
			}else {
				keyList.add(Integer.toString(i));
			}
		}
		return keyList;
	};

	public static JSONArray convertExcelToJson(String fileName, boolean isHeaderAvailable){
		JSONArray excelObject = new JSONArray();
		Sheet sheet;
		FileInputStream fis;
		Workbook workbook = null;
		String fileExt = FilenameUtils.getExtension(fileName);
		try {
			fis = new FileInputStream(fileName);
			workbook = (fileExt.equalsIgnoreCase(AppConstants.XLS_EXTENSION)) ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			List<String> keyList = generateKeysForExcel(sheet.getRow(0), isHeaderAvailable);
			int counterStart = isHeaderAvailable ? 1 : 0;
			for(int ctr = counterStart ;ctr< totalRows; ctr++) {
				Row row = sheet.getRow(ctr);
				int cellsCount = row.getLastCellNum();
				JSONObject rowObj = new JSONObject();
				for(int cellCtr = 0 ; cellCtr< cellsCount; cellCtr++) {
					String key = keyList.get(cellCtr);
					String value = CommonUtils.getStringContentFromExcelCell(row.getCell(cellCtr));
					rowObj.put(key, value);
				}
				excelObject.put(rowObj);
			}
		}catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return excelObject;
	}
	
	public static List<String> generateKeysForCsv(String row, String splitter, boolean isHeaderAvailable){
		List<String> keyList = new ArrayList<String>();
		List<String> blocks = Arrays.asList(row.split(splitter));
		int blocksCount = blocks.size();
		for(int i = 0 ; i < blocksCount; i++) {
			if(isHeaderAvailable) {
				keyList.add(blocks.get(i));
			}else {
				keyList.add(Integer.toString(i));
			}
		}
		return keyList;
	};
	
	public static JSONArray convertCSVToJson(String fileName, String splitter, boolean isHeaderAvailable){
		JSONArray csvObject = new JSONArray();
		String fileContents = getMessageContentsFromFile(fileName);
		List<String> lines = Arrays.asList(fileContents.split("\n"));
		int totalRows = lines.size();
		if(totalRows > 0) {
			List<String> keyList = generateKeysForCsv(lines.get(0), splitter, isHeaderAvailable);
			int counterStart = isHeaderAvailable ? 1 : 0;
			for(int ctr = counterStart ;ctr< totalRows; ctr++) {
				String record = lines.get(ctr);
				List<String> cells = Arrays.asList(record.split(splitter));
				JSONObject rowObj = new JSONObject();
				int cellsCount = cells.size();
				for(int cellCtr = 0 ; cellCtr< cellsCount; cellCtr++) {
					String key = keyList.get(cellCtr).replace("\"", "").trim();
					String value = cells.get(cellCtr).replace("\"", "").trim();
					rowObj.put(key.toUpperCase(), value);
				}
				csvObject.put(rowObj);
			}
		}
		return csvObject;
	}
	
	public static String getStringContentFromExcelCell(Cell cellInst) {
		String result = "";
		if(cellInst != null) {
			DataFormatter dataFormatter = new DataFormatter();
	        result = dataFormatter.formatCellValue(cellInst).trim();			
		}
		return result;
	}
	
	@SuppressWarnings("serial")
	private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
		put("^\\d{8}$", "yyyyMMdd");
		put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
		put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "dd/MM/yyyy");
		put("^\\d{1,2}[.]\\d{1,2}[.]\\d{4}$", "dd.MM.yyyy");
		put("^\\d{1,2}-\\d{1,2}-\\d{2}$", "dd-MM-yy");
		put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
		put("^\\d{1,2}/\\d{1,2}/\\d{2}$", "dd/MM/yy");
		put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
		put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
		put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
		put("^\\d{12}$", "yyyyMMddHHmm");
		put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
		put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
		put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
		put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
		put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
		put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
		put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
		put("^\\d{1,2}-[a-z]{3}-\\d{2}$", "dd-MMM-yy");
		put("^\\d{1,2}/[a-z]{3}/\\d{2}$", "dd/MMM/yy");
		put("^\\d{1,2}-[a-z]{3}-\\d{4}$", "dd-MMM-yyyy");
		put("^\\d{1,2}/[a-z]{3}/\\d{4}$", "dd/MMM/yyyy");
		put("^\\d{14}$", "yyyyMMddHHmmss");
		put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
		put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
		put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
		put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd/MM/yyyy HH:mm:ss");
		put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
		put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
		put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
	}};

	public static String determineDateFormat(String dateString) {
		for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
			if (dateString.toLowerCase().matches(regexp)) {
				return DATE_FORMAT_REGEXPS.get(regexp);
			}
		}
		return null; 
	}
	
	public static String getFileHeaders(String filePath) {
		String line = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			line = bufferedReader.readLine().toUpperCase();
			bufferedReader.close();
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return line;
	}
	
	public static String removeCommaFromValue(String value) {
		return value.replaceAll(",", "");
	}
	
	public static List<String> getLastTwelthMonths(String maxDate) {
		List<String> allMonthsList = new ArrayList<>();
		try {
			SimpleDateFormat monthDate = new SimpleDateFormat("MMM-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(monthDate.parse(maxDate));
			for (int i = 1; i <= 12; i++) {
			    String month_name1 = monthDate.format(cal.getTime());
			    allMonthsList.add(month_name1);
			    cal.add(Calendar.MONTH, -1);
			}
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return allMonthsList;
	}
	
	public static void writeCSV(String fileName, String csvContent) {
		FileWriter fileWriter = null;
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.getParentFile().mkdirs();
			}else {
				file.deleteOnExit();
			}
			fileWriter = new FileWriter(fileName);
			fileWriter.append(csvContent);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
			}
		}
	}
	
	public static int createZipFiles(String zipFile, List<String> srcFiles) {
		int isCreated=0;
		try {
			// create byte buffer
			File zip = new File(zipFile);
			if(!zip.exists())
				zip.getParentFile().mkdirs();

			byte[] buffer 			= new byte[1024];
			FileOutputStream fos 	= new FileOutputStream(zipFile);
			ZipOutputStream zos 	= new ZipOutputStream(fos);
			int srcFilesSize 		= srcFiles.size();
			for (int i = 0; i < srcFilesSize; i++) {
				File srcFile = new File(srcFiles.get(i));
				FileInputStream fis = new FileInputStream(srcFile);
				// begin writing a new ZIP entry, positions the stream to the start of the entry data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}
				zos.closeEntry();
				// close the InputStream
				fis.close();
			}
			// close the ZipOutputStream
			zos.close();
			isCreated = 1;
		}
		catch (IOException ioe) {
		}
		return isCreated;
	}
	
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 64;
	
	public static String generateRandomString() {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	
	public static String base64Encode(String originalString) {
		return new String(Base64.getEncoder().encode(originalString.getBytes()));
	}

	public static String base64Decode(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString));
	}
}
