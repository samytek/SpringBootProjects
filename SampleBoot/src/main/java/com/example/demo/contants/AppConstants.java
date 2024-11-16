package com.example.demo.contants;

public class AppConstants {

	public static final String CSV_EXTENSION = "csv";
	public static final String XLS_EXTENSION = "xls";
	public static final String XLSX_EXTENSION = "xlsx";
	public static final String DATA_EXTENSION = "DATA";

	public static final String USER = "USER";
	public static final String DEPT = "DEPT";
	
	public static final String ROOT_PATH = "C:/Users/Asus/Desktop/";
	public static final String SENDER_MAIL_ID = "senderMailId";
	public static final String SENDER_PWD = "senderPwd";
	public static final String SMTP_HOST = "smtpHost";
	public static final String SMTP_PORT = "smtpPort";
	public static final String FILE_GENERATOR_FLAG = "fileGeneratorFlag";
	public static final String LOGIN_CREDENTIALS = "loginCredentials";

	public static String rootPath 				= "";	
	public static String senderMailId 			= "";	
	public static String senderPwd 				= "";	
	public static String smtpHost 				= "";	
	public static String smtpPort 				= "";	
	public static String fileGeneratorFlag 		= "";	
	public static String loginCredentials 		= "";	
	
	public static final String USER_ID = "userId";
	public static final String USER_ROLE = "userRole";
	public static final String USER_INFO = "userInfo";
	public static final String USER_NAME = "userName";

	public static final String FAILED = "failed";
	public static final String SUCCESS = "success";
	
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String COMMON_ERROR = "CommonError";	

	public static final String STATUS = "status";
	public static final String STATUS_CODE = "statusCode";
	public static final String RESPONSE_STATUS = "responseStatus";
	public static final String MESSAGE = "message";

	public static final String RESPONSE_MESSAGE = "message";
	public static final String STATUS_MESSAGE = "statusMessage";
	
	public static final String GEN_EXCEL = "EXCEL";
	public static final String GEN_CSV = "CSV";
	
	public static final String BLANK = "";
	
	public static final String COMMA_QOUTES_SEPARATOR 			= ",\"";
	public static final String COMMA_SEPARATOR 					= ",";
	public static final String SLASH_PIPE_SEPARATOR 			= "\\|";
	public static final String PAIRED_COMMA_SEPARATOR 			= ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	
	public static final String USER_FILE_HEADERS = "REF NO|USER ID|USER NAME|USER ROLE|USER EMAIL|MOBILE NO|DEPT NAME";
	public static final String DEPT_FILE_HEADERS = "REF NO|USER ID|DEPT ID|DEPT NAME|DEPT DESC|CREATED DATE";
	
	public static final String NEWLINE = "\n";
	
	public static final long OTP_VALID_DURATION = 5 * 60 * 1000;

	public static final String DD_MM_YY_H_MM_SS_A = "DD-MM-YY_H:MM:SS_A";
}
