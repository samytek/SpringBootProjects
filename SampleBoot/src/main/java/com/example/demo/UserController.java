package com.example.demo;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.contants.AppConstants;
import com.example.demo.model.Users;
import com.example.demo.service.DeptDetailsService;
import com.example.demo.service.GenerateDataZipService;
import com.example.demo.service.GeneratePdfService;
import com.example.demo.service.GenerateXmlFileFromObjectService;
import com.example.demo.service.SendTestEmail;
import com.example.demo.service.UserDetailsService;
import com.example.demo.service.UserRolesService;
import com.example.demo.service.UserService;
import com.example.demo.service.XmlFileParsingService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
//
//	@AutowiredO
//	SendTestEmail sendTestEmail;

	@Autowired
	UserRolesService userRolesService;

//	@Autowired
//	SendEmailService sendEmailService;

	@Autowired
	DeptDetailsService deptDetailsService;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	GeneratePdfService generatePdfService;

	@Autowired
	XmlFileParsingService xmlFileParsingService;

	@Autowired
	GenerateDataZipService generateDataZipService;

	@Autowired
	GenerateXmlFileFromObjectService generateXmlFileFromObjectService;

	@PostMapping("/save")
	public int saveBook(@RequestBody Users users) {
		userService.addPerson(users);
		return users.getId();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/generatePDF", method = RequestMethod.POST)
	public ResponseEntity generatePDF() {
		generatePdfService.generatePdf();
		return new ResponseEntity(AppConstants.SUCCESS, HttpStatus.OK);
	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
//	public ResponseEntity sendEmail(@RequestBody String requestData) {
//		JSONObject requestJSONObj = new JSONObject(requestData);
//		JSONObject respJSONObj = sendEmailService.sendEmail(requestJSONObj);
//		return new ResponseEntity(respJSONObj.toString(), HttpStatus.OK);
//	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/verifyOTP", method = RequestMethod.POST)
//	public ResponseEntity verifyOTP(@RequestBody String requestData) {
//		JSONObject requestJSONObj = new JSONObject(requestData);
//		JSONObject respJSONObj = sendEmailService.verifyOTP(requestJSONObj);
//		return new ResponseEntity(respJSONObj.toString(), HttpStatus.OK);
//	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/generateZipReportFile", method = RequestMethod.POST)
	public ResponseEntity generateZipDataReportFile(@RequestBody String requestData) {
		JSONObject requestJSONObj = new JSONObject(requestData);
		JSONObject responseJSONObj = generateDataZipService.generatingDataReportFile(requestJSONObj);
		return new ResponseEntity(responseJSONObj.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getMonthlyData", method = RequestMethod.POST)
	public ResponseEntity getMonthlyData(@RequestBody String requestData) {
		JSONObject requestJSONObj = new JSONObject(requestData);
		JSONObject responseJSONObj = deptDetailsService.getMonthlyData(requestJSONObj);
		return new ResponseEntity(responseJSONObj.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	public ResponseEntity getData(@RequestBody String requestData) {
		JSONObject requestJSONObj = new JSONObject(requestData);
		JSONObject responseJSONObj = deptDetailsService.getData(requestJSONObj);
		return new ResponseEntity(responseJSONObj.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/XmlFileFromObject", method = RequestMethod.POST)
	public ResponseEntity XmlFileFromObject(@RequestBody String requestData) throws Exception {
		generateXmlFileFromObjectService.XmlFileFromObject();
		return new ResponseEntity("success", HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/parseXmlToJavaObject", method = RequestMethod.POST)
	public ResponseEntity parseXmlToJavaObject() throws Exception {
		xmlFileParsingService.parseXmlToJavaObject();
		return new ResponseEntity("success", HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/uploadFile", produces = { "application/json",
			"application/xml" }, method = RequestMethod.POST)
	public ResponseEntity<String> uploadCb1File(@RequestParam("passFile") MultipartFile passFile) {
		String fileName = passFile.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(fileName);
		JSONObject responseDataJSONObj = new JSONObject();
		if (fileExtension.equals(AppConstants.CSV_EXTENSION)) {
			responseDataJSONObj = userDetailsService.uploadUserMaster(passFile);
		} else {
			responseDataJSONObj = deptDetailsService.uploadDeptDeptMaster(passFile);
		}
		return new ResponseEntity(responseDataJSONObj.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
	public ResponseEntity getUserDetails() throws Exception {
		JSONObject responseJSON = userDetailsService.getDetails();
		return new ResponseEntity(responseJSON.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getDeptDetails", method = RequestMethod.POST)
	public ResponseEntity getDeptDetails() throws Exception {
		JSONObject responseJSON = deptDetailsService.getDetails();
		return new ResponseEntity(responseJSON.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/downloadZip", method = RequestMethod.POST)
	public ResponseEntity downloadZip() throws Exception {
		JSONObject responseJSON = generateDataZipService.downloadZipFile();
		return new ResponseEntity(responseJSON.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public ResponseEntity downloadFile() throws Exception {
		JSONObject responseJSON = generatePdfService.downloadFile();
		return new ResponseEntity(responseJSON.toString(), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getUserMapping", method = RequestMethod.POST)
	public ResponseEntity getUserMapping(@RequestBody String requestData) {
		JSONObject requestJSONObj = new JSONObject(requestData);
		JSONObject responseJSON = userRolesService.getUserMapping(requestJSONObj);
		return new ResponseEntity(responseJSON.toString(), HttpStatus.OK);
	}

////	@PreAuthorize("hasRole('NORMAL')")
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/sendSimpleEmail", method = RequestMethod.GET)
//	public String sendSimpleEmail() {
//		sendTestEmail.sendSimpleEmail("samshaikh5741@gmail.com", "Test Email", "Test Email");
//		return "Email sent successfully!";
//	}
}
