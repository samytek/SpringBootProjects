package com.example.demo.job;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.service.DataProcessService;
import com.example.demo.service.GenerateDataZipService;

//@Configuration
//@EnableScheduling
public class DataJob {

	private static final Logger logger = LoggerFactory.getLogger(DataJob.class);

	@Autowired
	DataProcessService dataProcessService;
	
	@Autowired
	GenerateDataZipService generateDataZipService;
	
	/*
	 * @Scheduled(fixedDelay = 60000) // Every 01 minute public void execute() {
	 * logger.
	 * info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Data Job Start >>>>>>>>>>>>>>>>>>>>>>> "
	 * +new Date()); uploadAllMastersFiles(); try { }catch(Exception e) {
	 * logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
	 * } logger.
	 * info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Data Job End >>>>>>>>>>>>>>>>>>>>>>> "+new
	 * Date()); }
	 */
	
	private void uploadAllMastersFiles() {
		String moduleType = null;
		String fileName = "";
		String rootPath = AppConstants.ROOT_PATH; 
		String filePath = rootPath + File.separator + "DataFiles" + File.separator;
		logger.info("::::: uploadAllMastersFiles File Path:::: "+filePath);
		File fileDir = new File(filePath);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		} else {
			logger.info("::::: uploadAllMastersFiles File List Size:::: "+fileDir.listFiles().length);
			for (File file : fileDir.listFiles()) {
				if(file.isFile()) {
					try {
						fileName = file.getName().split("_")[0]; 
						logger.info("::::: uploadAllMastersFiles File Name ::::" + fileName.toUpperCase());
						moduleType = FilenameUtils.getBaseName(fileName);
						logger.info("::::: uploadAllMastersFiles Module Type ::::" + moduleType.toUpperCase());
						dataProcessService.uploadMasterFilesToDB(filePath, file.getName());
					} catch(Exception e) {
						logger.error(ErrorHandler.getErrorMessageLog(new Object(){}.getClass(), e));
					}
				}
			}
		}			
	}
}
