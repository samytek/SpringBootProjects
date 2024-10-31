package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.model.DeptDetails;
import com.example.demo.repository.DeptDetailsDAO;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.DateUtils;

@Service
public class DeptDetailsServiceImpl implements DeptDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(DeptDetailsServiceImpl.class);

	@Autowired
	DeptDetailsDAO deptDetailsDAO;

	@Override
	public JSONObject uploadDeptDeptMaster(MultipartFile file) {
		JSONObject responseJson = new JSONObject();
		try {
			String fileName = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(fileName);
			String rootPath = AppConstants.ROOT_PATH;

			boolean isValidFileExtension = getFileDetailsByModuleName(fileExtension);
			if (!isValidFileExtension) {
				responseJson.put(AppConstants.RESPONSE_MESSAGE, "Invalid file format");
				responseJson.put(AppConstants.STATUS, AppConstants.FAILED);
				return responseJson;
			}
			String fileNameSplit = fileName.split("_")[0];
			String moduleType = FilenameUtils.getBaseName(fileNameSplit);
			if (!AppConstants.DEPT.equalsIgnoreCase(moduleType)) {
				responseJson.put(AppConstants.RESPONSE_MESSAGE, "Invalid file Type");
				responseJson.put(AppConstants.STATUS, AppConstants.FAILED);
				return responseJson;
			}

			String filePath = rootPath + "/";
			logger.info("::::: File Path ::::: " + filePath);
			File fileDir = new File(filePath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			String absoluteFilePath = filePath + File.separator + fileName;

			File destinationFile = new File(absoluteFilePath);
			file.transferTo(destinationFile);
			responseJson.put(AppConstants.RESPONSE_MESSAGE, "File Uploaded Successfully.");
			responseJson.put(AppConstants.STATUS, AppConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {
			}.getClass(), e));
		}
		return responseJson;
	}

	public boolean getFileDetailsByModuleName(String moduleName) {
		boolean isValidFileExtension = Boolean.FALSE;
		if (CommonUtils.isNotNullAndNotEmpty(moduleName) && (moduleName.equalsIgnoreCase(AppConstants.XLS_EXTENSION)
				|| moduleName.equalsIgnoreCase(AppConstants.XLSX_EXTENSION))) {
			isValidFileExtension = Boolean.TRUE;
		}
		return isValidFileExtension;
	}

	@SuppressWarnings({ "finally", "resource" })
	@Override
	public boolean saveDeptMaster(String fileName, FileInputStream fileInstance) {
		boolean fileProcessed = false;
		logger.info("saveDeptPurposeCodeMasterData() :: Processing File : " + fileName);
		try {
			Sheet sheet;
			String fileExt = FilenameUtils.getExtension(fileName);
			if (fileExt.equalsIgnoreCase(AppConstants.XLS_EXTENSION)) {
				HSSFWorkbook workbook = new HSSFWorkbook(fileInstance);
				sheet = workbook.getSheetAt(0);
			} else {
				XSSFWorkbook workbook = new XSSFWorkbook(fileInstance);
				sheet = workbook.getSheetAt(0);
			}
			Row row = sheet.getRow(0);
			int currentRowInExcel = 0;
			for (currentRowInExcel = 1; currentRowInExcel < sheet.getPhysicalNumberOfRows(); currentRowInExcel++) {
				row = sheet.getRow(currentRowInExcel);
				DeptDetails deptDetailsInst = new DeptDetails();
				int colIdx = 0;
				deptDetailsInst.setUserId(CommonUtils.getUpperStringFromExcelCell(row.getCell(colIdx++)));
				deptDetailsInst.setDeptId(CommonUtils.getUpperStringFromExcelCell(row.getCell(colIdx++)));
				deptDetailsInst.setDeptName(CommonUtils.getUpperStringFromExcelCell(row.getCell(colIdx++)));
				deptDetailsInst.setDeptDesc(CommonUtils.getUpperStringFromExcelCell(row.getCell(colIdx++)));
				deptDetailsInst.setCreatedDate(DateUtils.getDateFromString(
						CommonUtils.getUpperStringFromExcelCell(row.getCell(colIdx++)), "dd/MM/yyyy"));
				deptDetailsDAO.save(deptDetailsInst);
				fileProcessed = true;
			}
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {
			}.getClass(), e));
		} finally {
			return fileProcessed;
		}
	}

	@Override
	public JSONObject getData(JSONObject reqJSON) {
		JSONObject respJSON = new JSONObject();

		String sDate = reqJSON.getString("sDate");
		String eDate = reqJSON.getString("eDate");
		Date fDate = DateUtils.getDateFromString(sDate, "dd-MM-yyyy");
		Date lDate = DateUtils.getDateFromString(eDate, "dd-MM-yyyy");
		List<DeptDetails> deptDetailsList = deptDetailsDAO.findByCreatedDateBetweenOrderByCreatedDateDesc(fDate, lDate);
		DeptDetails deptDetailsInst = deptDetailsList.get(0);
		System.out.println(deptDetailsInst);
		return respJSON;
	}
	
	@Override
	public JSONObject getMonthlyData(JSONObject reqJSON) {
		JSONObject respJSON = new JSONObject();
		String sDate = null;
		String eDate = null;
		String month = reqJSON.getString("month");
		String year = reqJSON.getString("year");
		String fortNight = reqJSON.getString("fortNight");
		if (fortNight.equalsIgnoreCase("firstFortNight")) {
			ArrayList<String> list = DateUtils.createFirstFortNightDate(month, year);
			sDate = list.get(0);
			eDate = list.get(1);
		} else if (fortNight.equalsIgnoreCase("secondFortNight")){
			String date = DateUtils.getLastDayOfMonthAsString(Integer.parseInt(year), Integer.parseInt(month));
			ArrayList<String> list = DateUtils.createSecondFortNightDate(date, month, year);
			sDate = list.get(0);
			eDate = list.get(1);
		}
		Date fDate = DateUtils.getDateFromString(sDate, "dd-MM-yyyy");
		Date lDate = DateUtils.getDateFromString(eDate, "dd-MM-yyyy");
		List<DeptDetails> deptDetailsList = deptDetailsDAO.findByCreatedDateBetweenOrderByCreatedDateDesc(fDate, lDate);
		System.out.println(deptDetailsList);
		return respJSON;
	}

	@Override
	public JSONObject getDetails() {
		JSONObject responseJSON = new JSONObject();
		List<DeptDetails> deptDetailsList = null;
		try {
			deptDetailsList = deptDetailsDAO.findAll();
		} catch (Exception e) {
			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
		}
		responseJSON.put(AppConstants.STATUS, AppConstants.SUCCESS);
		responseJSON.put("deptDetailsList", deptDetailsList);
		return responseJSON;
	}
}

