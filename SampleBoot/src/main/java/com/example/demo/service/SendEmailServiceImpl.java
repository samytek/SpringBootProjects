package com.example.demo.service;

//import java.util.Base64;
//import java.util.Date;
//import java.util.Properties;
//import java.util.Random;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.contants.AppConstants;
//import com.example.demo.exception.ErrorHandler;
//import com.example.demo.model.EmailOtpTracker;
//import com.example.demo.repository.AppConstantsDAO;
//import com.example.demo.repository.EmailOtpTrackerDAO;
//import com.example.demo.utils.CommonUtils;

//@Service
//public class SendEmailServiceImpl implements SendEmailService {
//
//	private static final Logger logger = LoggerFactory.getLogger(SendEmailServiceImpl.class);
//
//	@Autowired
//	AppConstantsDAO appConstantsDAO;
//
//	@Autowired
//	EmailOtpTrackerDAO emailOtpTrackerDAO;

//	@Override
//	public JSONObject sendEmail(JSONObject jsonReq) {
//		
//		String emailId = jsonReq.optString("email");
//
//		String senderMailId = appConstantsDAO.findConstantByConstantName("senderMailId").getConstantValue();
//		String senderPwd = appConstantsDAO.findConstantByConstantName("senderPwd").getConstantValue();
//		String smtpHost = appConstantsDAO.findConstantByConstantName("smtpHost").getConstantValue();
//		String smtpPort = appConstantsDAO.findConstantByConstantName("smtpPort").getConstantValue();
//
//		JSONObject responseJson = new JSONObject();
//		Properties properties = System.getProperties();
//		properties.put("mail.smtp.host", smtpHost);
//		properties.put("mail.smtp.port", smtpPort);
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//
//		byte[] decodedBytes = Base64.getDecoder().decode(senderPwd);
//		String decodedSenderPswd = new String(decodedBytes);
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(senderMailId, decodedSenderPswd);
//			}
//		});
//
////		session.setDebug(true);
//		MimeMessage m = new MimeMessage(session);
//		try {
//			StringBuilder otp = new StringBuilder();
//			Random random = new Random();
//			for (int i = 0; i < 6; i++) {
//				int digit = random.nextInt(10);
//				otp.append(digit);
//			}
//			
//			m.setFrom(senderMailId);
//			m.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
//			m.setSubject("OTP:: "+otp.toString());
////			String path = "C:\\Users\\user\\Desktop\\ca_logo.png";
//			MimeMultipart mimeMultipart = new MimeMultipart();
//			MimeBodyPart textMime = new MimeBodyPart();
////			MimeBodyPart fileMime = new MimeBodyPart();
//			textMime.setText("Hello, this is test mail");
////			File file = new File(path);
////			fileMime.attachFile(file);
//			mimeMultipart.addBodyPart(textMime);
////			mimeMultipart.addBodyPart(fileMime);
//
//			saveEmailOtpTracker(emailId, otp.toString());
//
//			m.setContent(mimeMultipart);
//			Transport.send(m);
//			responseJson.put(AppConstants.STATUS, AppConstants.SUCCESS);
//			responseJson.put(AppConstants.MESSAGE, "OTP Sent Successfully");
//		} catch (Exception e) {
//			logger.error(ErrorHandler.getErrorMessageLog(new Object() {}.getClass(), e));
//
//		}
//		return responseJson;
//	}
//
//	private void saveEmailOtpTracker(String emailId, String otp) {
//		EmailOtpTracker emailOtpTracker = new EmailOtpTracker();
//		emailOtpTracker.setGeneratedOtp(Integer.parseInt(otp.toString()));
//		emailOtpTracker.setOtpSendOnEmail(emailId.toString());
//		emailOtpTracker.setAuthenticated(false);
//		emailOtpTracker.setExpired(false);
//		emailOtpTracker.setOtpGenerateDateTime(new Date());
//		emailOtpTrackerDAO.save(emailOtpTracker);
//	}
//	
//	@Override
//	public JSONObject verifyOTP(JSONObject requestJson) {
//		JSONObject responseJson = new JSONObject();
//		
//		int otp = requestJson.optInt("otp");
//		String emailId = requestJson.optString("email");
//		
//		EmailOtpTracker emailOtpTrackerInst = emailOtpTrackerDAO.findByGeneratedOtpAndOtpSendOnEmail(otp, emailId);
//		if(CommonUtils.isNotNullAndNotEmpty(emailOtpTrackerInst)) {
//	        if(otp == emailOtpTrackerInst.getGeneratedOtp() && !emailOtpTrackerInst.isAuthenticated() && !emailOtpTrackerInst.isExpired()) {
//	        	emailOtpTrackerInst.setAuthenticated(true);
//	        	emailOtpTrackerDAO.save(emailOtpTrackerInst);
//	        	
//	        	responseJson.put(AppConstants.STATUS, AppConstants.SUCCESS);
//	        	responseJson.put(AppConstants.MESSAGE, "OTP Validated Successfully.");
//	        } else {
//	        	responseJson.put(AppConstants.STATUS, AppConstants.FAILED);
//	        	responseJson.put(AppConstants.MESSAGE, "OTP Validation Failed.");
//	        }
//		}
//		return responseJson;
//	}
//}
