package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.model.AppCommonConstants;
import com.example.demo.model.DeptDetails;
import com.example.demo.model.UserDetails;
import com.example.demo.repository.AppConstantsDAO;
import com.example.demo.repository.DeptDetailsDAO;
import com.example.demo.repository.UserDetailsDAO;
import com.example.demo.utils.CommonUtils;

@Service
public class GenerateDataZipServiceImpl implements GenerateDataZipService {

	private static final Logger logger = LoggerFactory.getLogger(GenerateDataZipServiceImpl.class);

	@Autowired
	UserDetailsDAO userDetailsDAO;

	@Autowired
	DeptDetailsDAO deptDetailsDAO;
	
	@Autowired
	AppConstantsDAO appConstantsDAO;
	
	@Override
	public JSONObject generatingDataReportFile(JSONObject requestJSON) {

		JSONObject responseJSONObj = new JSONObject();
		String absoluteDownloadFilePath = AppConstants.ROOT_PATH + File.separator + "Output_Files" + File.separator;
		List<String> srcFiles = new ArrayList<>();
		List<UserDetails> UserDetailsList = userDetailsDAO.findAll();
		List<DeptDetails> deptDetailsList = deptDetailsDAO.findAll();

		AppCommonConstants appCommonConstantsObj = appConstantsDAO.findConstantByConstantName("fileGeneratorFlag");
		JSONObject responseJSON = new JSONObject();
		try {
			if (UserDetailsList.size() > 0 && deptDetailsList.size() > 0 && appCommonConstantsObj.getConstantValue().equalsIgnoreCase(AppConstants.GEN_EXCEL)) {
				responseJSON = generateUserExcel(absoluteDownloadFilePath, UserDetailsList);
				srcFiles.add(responseJSON.optString("filePath"));
				responseJSON = generateDeptExcel(absoluteDownloadFilePath, deptDetailsList);
				srcFiles.add(responseJSON.optString("filePath"));
			}
			if (UserDetailsList.size() > 0 && deptDetailsList.size() > 0 && appCommonConstantsObj.getConstantValue().equalsIgnoreCase(AppConstants.GEN_CSV)) {
				responseJSON = generateUserCsv(absoluteDownloadFilePath, UserDetailsList);
				srcFiles.add(responseJSON.optString("filePath"));
				responseJSON = generateDeptCsv(absoluteDownloadFilePath, deptDetailsList);
				srcFiles.add(responseJSON.optString("filePath"));
			}
			String fileMonth = CommonUtils.getDateInStringFormat("ddMMyyyyhhMMss", new Date());
			int fileCreated = CommonUtils.createZipFiles(absoluteDownloadFilePath + "Data_" + fileMonth + ".zip", srcFiles);
			if (fileCreated > 0) {
				logger.error("File Created Successfully");
				deleteFiles(absoluteDownloadFilePath);
				responseJSONObj.put(AppConstants.STATUS, AppConstants.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {
			}.getClass(), e));
		}
		return responseJSONObj;

	}

	private JSONObject generateUserExcel(String absoluteDownloadExcleFilePath, List<UserDetails> userDataList) {
		JSONObject responseJSONObj = new JSONObject();
		String fileName = "";
		try {
			String finalFileName = "USER_DETAILS.xlsx";
			fileName = absoluteDownloadExcleFilePath + finalFileName;
			String fileHeader = AppConstants.USER_FILE_HEADERS;
			String headerFormat = fileHeader.replace("|", ",");
			String[] headerFormatSplit = headerFormat.split(",");
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			Row headerRow = sheet.createRow(0);
			int headerSize = headerFormatSplit.length;

			for (int i = 0; i < headerSize; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headerFormatSplit[i]);
			}
			int rowNum = 1;
			for (UserDetails userDataObj : userDataList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(rowNum);
				row.createCell(1).setCellValue(CommonUtils.isNotNullAndNotEmpty(userDataObj.getEmpId()) ? userDataObj.getEmpId() : "");
				row.createCell(2).setCellValue(CommonUtils.isNotNullAndNotEmpty(userDataObj.getUserName()) ? userDataObj.getUserName() : "");
				row.createCell(3).setCellValue(CommonUtils.isNotNullAndNotEmpty(userDataObj.getUserRole()) ? userDataObj.getUserRole() : "");
				row.createCell(4).setCellValue(CommonUtils.isNotNullAndNotEmpty(userDataObj.getUserEmail()) ? userDataObj.getUserEmail() : "");
				row.createCell(5).setCellValue(CommonUtils.isNotNullAndNotEmpty(userDataObj.getMobNo()) ? userDataObj.getMobNo() : "");
				row.createCell(6).setCellValue(CommonUtils.isNotNullAndNotEmpty(userDataObj.getDeptName()) ? userDataObj.getDeptName() : "");
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
			} catch (IOException e) {
				logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
			}
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		responseJSONObj.put("filePath", fileName);
		return responseJSONObj;
	}

	private JSONObject generateDeptExcel(String absoluteDownloadExcleFilePath, List<DeptDetails> deptDataList) {
		JSONObject responseJSONObj = new JSONObject();
		String fileName = "";
		try {
			String finalFileName = "DEPT_DETAILS.xlsx";
			fileName = absoluteDownloadExcleFilePath + finalFileName;
			String fileHeader = AppConstants.DEPT_FILE_HEADERS;
			String headerFormat = fileHeader.replace("|", ",");
			String[] headerFormatSplit = headerFormat.split(",");
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			Row headerRow = sheet.createRow(0);
			int headerSize = headerFormatSplit.length;

			for (int i = 0; i < headerSize; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headerFormatSplit[i]);
			}
			int rowNum = 1;
			for (DeptDetails deptDataObj : deptDataList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(rowNum);
				row.createCell(1).setCellValue(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getUserId()) ? deptDataObj.getUserId() : "");
				row.createCell(2).setCellValue(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getDeptId()) ? deptDataObj.getDeptId() : "");
				row.createCell(3).setCellValue(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getDeptName()) ? deptDataObj.getDeptName() : "");
				row.createCell(4).setCellValue(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getDeptDesc()) ? deptDataObj.getDeptDesc() : "");
//				row.createCell(5).setCellValue(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getCreatedDate()) ? deptDataObj.getCreatedDate() : "");
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
			} catch (IOException e) {
				logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
			}
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		responseJSONObj.put("filePath", fileName);
		return responseJSONObj;
	}

	private JSONObject generateUserCsv(String absoluteDownloadCsvFilePath, List<UserDetails> userDataList) {
		JSONObject responseJSONObj = new JSONObject();
		String fileName = "";
		try {
			String finalFileName = "USER_DETAILS.csv";
			fileName = absoluteDownloadCsvFilePath + finalFileName;
			String headerFormat = AppConstants.USER_FILE_HEADERS;
			String fileHeader = headerFormat.replace("|", ",");
			StringBuilder csvBody = new StringBuilder();
			int num = 1;
			for (UserDetails userDataObj : userDataList) {
				csvBody.append(num++).append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getEmpId())	? CommonUtils.removeCommaFromValue(userDataObj.getEmpId()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getUserName())	? CommonUtils.removeCommaFromValue(userDataObj.getUserName()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getUserRole())	? CommonUtils.removeCommaFromValue(userDataObj.getUserRole()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getUserEmail())	? CommonUtils.removeCommaFromValue(userDataObj.getUserEmail()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getMobNo())	? CommonUtils.removeCommaFromValue(userDataObj.getMobNo()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getDeptName()) ? CommonUtils.removeCommaFromValue(userDataObj.getDeptName()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(userDataObj.getLastPswd())	? CommonUtils.removeCommaFromValue(userDataObj.getLastPswd()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(AppConstants.NEWLINE);
			}
			fileHeader = fileHeader + AppConstants.NEWLINE;
			String csvContent = fileHeader + csvBody;
			CommonUtils.writeCSV(fileName, csvContent);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		responseJSONObj.put("filePath", fileName);
		return responseJSONObj;
	}

	private JSONObject generateDeptCsv(String absoluteDownloadCsvFilePath, List<DeptDetails> deptDataList) {
		JSONObject responseJSONObj = new JSONObject();
		String fileName = "";
		try {
			String finalFileName = "DEPT_DETAILS.csv";
			fileName = absoluteDownloadCsvFilePath + finalFileName;
			String headerFormat = AppConstants.DEPT_FILE_HEADERS;
			String fileHeader = headerFormat.replace("|", ",");
			StringBuilder csvBody = new StringBuilder();
			int num = 1;
			for (DeptDetails deptDataObj : deptDataList) {
				csvBody.append(num++).append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getUserId()) ? CommonUtils.removeCommaFromValue(deptDataObj.getUserId()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getDeptId()) ? CommonUtils.removeCommaFromValue(deptDataObj.getDeptId()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getDeptName()) ? CommonUtils.removeCommaFromValue(deptDataObj.getDeptName()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getDeptDesc())	? CommonUtils.removeCommaFromValue(deptDataObj.getDeptDesc()): "").append(AppConstants.COMMA_SEPARATOR);
//				csvBody.append(CommonUtils.isNotNullAndNotEmpty(deptDataObj.getCreatedDate()) ? CommonUtils.removeCommaFromValue(deptDataObj.getCreatedDate()): "").append(AppConstants.COMMA_SEPARATOR);
				csvBody.append(AppConstants.NEWLINE);
			}
			fileHeader = fileHeader + AppConstants.NEWLINE;
			String csvContent = fileHeader + csvBody;
			CommonUtils.writeCSV(fileName, csvContent);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		responseJSONObj.put("filePath", fileName);
		return responseJSONObj;
	}

	private void deleteFiles(String filePath) {
		File fileDir = new File(filePath);
		if (CommonUtils.isNotNullAndNotEmpty(fileDir)) {
			for (File file : fileDir.listFiles()) {
				String fileName = file.getName();
				String ext = FilenameUtils.getExtension(fileName);
				if (ext.equals("xlsx") || ext.equals("csv")) {
					file.delete();
				}
			}
		}
	}

	@Override
	public JSONObject downloadZipFile() {
		String finalFilePath 			= "C:/Users/Asus/Desktop/Output_Files/DATA.zip";
		JSONObject  responseDataJSONObj = new JSONObject();
		try {
			File file 		= new File(finalFilePath);
			if(file.exists()) {
				byte[] bytes 	= Files.readAllBytes(file.toPath());
				responseDataJSONObj.put(AppConstants.STATUS, AppConstants.SUCCESS);
				responseDataJSONObj.put("fileData", bytes);
				responseDataJSONObj.put("fileName", "Data.zip");
				responseDataJSONObj.put(AppConstants.RESPONSE_MESSAGE, " File downloaded successfully ");
			} else {
				responseDataJSONObj.put(AppConstants.STATUS, AppConstants.FAILED);
				responseDataJSONObj.put(AppConstants.RESPONSE_MESSAGE, "File Not Found.");
			}
		} catch(Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
		}
		return responseDataJSONObj;
	}
}
